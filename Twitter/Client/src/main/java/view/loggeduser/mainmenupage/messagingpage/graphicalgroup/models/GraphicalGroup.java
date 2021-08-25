package view.loggeduser.mainmenupage.messagingpage.graphicalgroup.models;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.NewChatMassageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.UpdateOfflineChatInfo;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.LeaveGroupEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.NewGroupMessageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.UpdateOfflineGroupInfo;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenBotChatHyperLinkEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenChatHyperLinkEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenGroupHyperLinkEvent;
import events.clientevents.main.ActivateWaintingThreadEvent;
import events.clientevents.main.ClientEvent;
import events.clientevents.main.InterruptWaitingThread;
import events.clientevents.main.MakeClientNetworkManagerWaiting;
import events.serverevents.luser.messagingpage.graphicalgroup.SendGroupMessagesEvent;
import events.serverevents.main.ServerEvent;
import models.messaging.ChatMassage;
import network.NetworkManager;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.messagingpage.GroupsPage;
import view.loggeduser.mainmenupage.messagingpage.graphicalchat.models.ChatPanel;
import view.loggeduser.mainmenupage.messagingpage.graphicalgroup.buttons.AddMembersButton;
import models.messaging.Group;
import view.loggeduser.mainmenupage.messagingpage.graphicalgroup.buttons.LeaveGroupButton;
import view.loggeduser.mainmenupage.messagingpage.graphicalgroup.buttons.ScheduleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

public class GraphicalGroup extends LUserPanel {
    private final ChatPanel chatPanel;
    private final GSendMessagePanel gSendMassagePanel;
    private final JScrollPane scrollPane;
    private final AddMembersButton addMembersButton;
    private final LeaveGroupButton leaveGroupButton;
    private final ScheduleButton scheduleButton;
    private final GroupsPage groupsPage;
    private final ArrayList<NewGroupMessageEvent> queuedEvents;
    private boolean connectionCheckerThreadActived;
    private final Group group;

    public GraphicalGroup(LUserPanel previousPage, Group group, GroupsPage groupsPage) {
        super(previousPage, previousPage.getMainPanel(), previousPage.getOwnerId());
        this.group = group;
        this.groupsPage = groupsPage;
        this.queuedEvents = new ArrayList<>();
        this.setLayout(null);
        this.setBounds(0, 0, 800, 740);
        this.setBackground(Color.GREEN);

        chatPanel = new ChatPanel(this, group.getChatMassages(), getOwnerId());
        chatPanel.setPreferredSize(new Dimension(60,(group.getChatMassages().size() * 400)));
        gSendMassagePanel = new GSendMessagePanel(this, group.getUsersIds());
        gSendMassagePanel.setBounds(0, 600, gSendMassagePanel.getWidth(), gSendMassagePanel.getHeight());
        scrollPane = new JScrollPane(chatPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
        scrollBar.setUnitIncrement(36);
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollToBottom(scrollPane);
        scrollPane.setBounds(0, 0, 500, 600);
        scrollPane.validate();
        scrollPane.repaint();

        this.add(scrollPane);
        this.add(gSendMassagePanel);

        addMembersButton = new AddMembersButton(this);
        addMembersButton.setText("Add Member");
        addMembersButton.setBounds(0, 670, 133, 30);
        this.add(addMembersButton);

        leaveGroupButton = new LeaveGroupButton(this);
        leaveGroupButton.setText("Leave");
        leaveGroupButton.setBounds(133, 670, 134, 30);
        this.add(leaveGroupButton);

        scheduleButton = new ScheduleButton(this);
        scheduleButton.setText("Schedule");
        scheduleButton.setBounds(267, 670, 133, 30);
        this.add(scheduleButton);

        this.repaint();
        this.revalidate();
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public void initializeChatPanel(){
        chatPanel.initialize();
        scrollToBottom(scrollPane);
    }

    public Group getGroup() {
        return group;
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

    public void sendNewGroupMassageEvent(NewGroupMessageEvent newGroupMessageEvent, boolean hasAnswer) throws Exception {
        if (NetworkManager.isConnectedToServer()){
            sendClientEvent(newGroupMessageEvent, hasAnswer);
        }else {
            if (!connectionCheckerThreadActived){
                activeConnectionCheckerThread();
            }
            manageOfflineMode(newGroupMessageEvent);
        }
    }


    private void manageOfflineMode(NewGroupMessageEvent newGroupMessageEvent){
        queuedEvents.add(newGroupMessageEvent);
        ArrayList<ChatMassage> chatPanelChatMessages = new ArrayList<>(chatPanel.getChatMassages());
        chatPanelChatMessages.add(new ChatMassage(getOwnerId(), newGroupMessageEvent.getText(), newGroupMessageEvent.getImagePath(), null));
        chatPanel.setChatMassages(chatPanelChatMessages);
        group.setChatMassages(chatPanelChatMessages);
        try {
            sendClientEvent(new UpdateOfflineGroupInfo(group), false);
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
                for (NewGroupMessageEvent newGroupMessageEvent : queuedEvents){
                    newGroupMessageEvent.setHasAnswer(false);
                    Thread.sleep(50);
                    sendClientEvent(newGroupMessageEvent, false);
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
        } else
        if (clientEvent.getClass() == LeaveGroupEvent.class){
            this.back();
            groupsPage.back();
        }

        super.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        interpretServerAnswer((ServerEvent) logicalEvent);

    }

    @Override
    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        if (serverEvent.getClass() == SendGroupMessagesEvent.class){
            chatPanel.setChatMassages(((SendGroupMessagesEvent) serverEvent).getChatMassages());
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
        if (groupsPage != null){
            groupsPage.setGroupPageShowed(false);
        }
        super.back();
    }

    public void openGroupPage(String token){
        this.back();
        groupsPage.openGroupPage(token);
    }

    public void openChatPage(String token){
        this.back();
        groupsPage.openChatPage(token);
    }

    public void openBotChatPage(String token){
        this.back();
        groupsPage.openBotChatPage(token);
    }
}
