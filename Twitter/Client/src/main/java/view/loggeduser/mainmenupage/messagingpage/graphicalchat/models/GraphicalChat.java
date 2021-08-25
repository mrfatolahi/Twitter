package view.loggeduser.mainmenupage.messagingpage.graphicalchat.models;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.NewChatMassageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.UpdateOfflineChatInfo;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenBotChatHyperLinkEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenChatHyperLinkEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenGroupHyperLinkEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.SettingPageEvent;
import events.clientevents.main.ActivateWaintingThreadEvent;
import events.clientevents.main.ClientEvent;
import events.clientevents.main.InterruptWaitingThread;
import events.clientevents.main.MakeClientNetworkManagerWaiting;
import events.serverevents.luser.messagingpage.graphicalchat.SendChatMessagesEvent;
import events.serverevents.main.ServerEvent;
import models.messaging.ChatMassage;
import network.NetworkManager;
import view.loggeduser.LUserPanel;
import models.messaging.Chat;
import view.loggeduser.mainmenupage.messagingpage.ChatsPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

public class GraphicalChat extends LUserPanel {
    private final ChatPanel chatPanel;
    private final SendMassagePanel sendMassagePanel;
    private final JScrollPane scrollPane;
    private final ChatsPage chatsPage;
    private final ArrayList<NewChatMassageEvent> queuedEvents;
    private boolean connectionCheckerThreadActived;
    private Chat chat;

    public GraphicalChat(LUserPanel previousPage, Chat chat, ChatsPage chatsPage) {
        super(previousPage, previousPage.getMainPanel(), previousPage.getOwnerId());
        this.connectionCheckerThreadActived = false;
        this.chat = chat;
        this.chatsPage = chatsPage;
        this.setLayout(null);
        this.setBounds(0, 0, 800, 740);
        this.setBackground(Color.GREEN);
        this.queuedEvents = new ArrayList<>();

        int receiverId = 0;
        if (this.chat.getUser1Id() != getOwnerId()){receiverId = this.chat.getUser1Id();} else {receiverId = this.chat.getUser2Id();}

        chatPanel = new ChatPanel(this, this.chat.getChatMassages(), getOwnerId());
        chatPanel.setPreferredSize(new Dimension(60,(this.chat.getChatMassages().size() * 400)));
        sendMassagePanel = new SendMassagePanel(this, receiverId);
        sendMassagePanel.setBounds(0, 600, 600, 100);
        scrollPane = new JScrollPane(chatPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
        scrollBar.setUnitIncrement(36);
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollToBottom(scrollPane);
        scrollPane.setBounds(0, 0, 500, 600);
        scrollPane.validate();
        scrollPane.repaint();

        this.add(scrollPane);
        this.add(sendMassagePanel);

        this.repaint();
        this.revalidate();
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public void initializeChatPanel(){
        chatPanel.initialize();
        chatPanel.validate();
        scrollToBottom(scrollPane);
    }

    private void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    public void sendNewChatMassageEvent(NewChatMassageEvent newChatMassageEvent, boolean hasAnswer) throws Exception {
        if (NetworkManager.isConnectedToServer()){
            sendClientEvent(newChatMassageEvent, hasAnswer);
        }else {
            if (!connectionCheckerThreadActived){
                activeConnectionCheckerThread();
            }
            manageOfflineMode(newChatMassageEvent);
        }
    }

    private void manageOfflineMode(NewChatMassageEvent newChatMassageEvent){
        queuedEvents.add(newChatMassageEvent);
        ArrayList<ChatMassage> chatPanelChatMessages = new ArrayList<>(chatPanel.getChatMassages());
        chatPanelChatMessages.add(new ChatMassage(getOwnerId(), newChatMassageEvent.getText(), newChatMassageEvent.getImagePath(), null));
        chatPanel.setChatMassages(chatPanelChatMessages);
        chat.setChatMassages(chatPanelChatMessages);
        try {
            sendClientEvent(new UpdateOfflineChatInfo(chat), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeChatPanel();
    }

    private void activeConnectionCheckerThread(){
        (new Thread(() -> {
            try {
                connectionCheckerThreadActived = true;
                synchronized (NetworkManager.connectionNotifier){
                    NetworkManager.connectionNotifier.wait();
                }
                for (NewChatMassageEvent newChatMassageEvent : queuedEvents){
                    newChatMassageEvent.setHasAnswer(false);
                    Thread.sleep(50);
                    sendClientEvent(newChatMassageEvent, false);
                }
                queuedEvents.clear();
                connectionCheckerThreadActived = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        })).start();
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        if (clientEvent.getClass() == OpenGroupHyperLinkEvent.class){
            openGroupPage(((OpenGroupHyperLinkEvent) clientEvent).getHyperlink().getToken());
            return;
        } else
        if (clientEvent.getClass() == OpenChatHyperLinkEvent.class){
            openChatPage(((OpenChatHyperLinkEvent) clientEvent).getHyperlink().getToken());
            return;
        } else
        if (clientEvent.getClass() == OpenBotChatHyperLinkEvent.class){
            openBotChatPage(((OpenBotChatHyperLinkEvent) clientEvent).getHyperlink().getToken());
            return;
        }
        super.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        interpretServerAnswer((ServerEvent) logicalEvent);
    }

    @Override
    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        if (serverEvent.getClass() == SendChatMessagesEvent.class){
            chatPanel.setChatMassages(((SendChatMessagesEvent) serverEvent).getChatMassages());
            initializeChatPanel();
            try {
                sendClientEvent(new MakeClientNetworkManagerWaiting(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void back() {
        try {
            sendClientEvent(new InterruptWaitingThread(), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (chatsPage != null){
            chatsPage.setChatPageShowed(false);
        }
        super.back();
    }

    public void openGroupPage(String token){
        this.back();
        chatsPage.openGroupPage(token);
    }

    public void openChatPage(String token){
        this.back();
        chatsPage.openChatPage(token);
    }

    public void openBotChatPage(String token){
        this.back();
        chatsPage.openBotChatPage(token);
    }
}
