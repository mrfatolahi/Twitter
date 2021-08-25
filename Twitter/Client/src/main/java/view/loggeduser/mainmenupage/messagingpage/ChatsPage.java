package view.loggeduser.mainmenupage.messagingpage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestChatInfoEvent;
import events.clientevents.main.ActivateWaintingThreadEvent;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.luser.messagingpage.SendChatInfoServerEvent;
import events.serverevents.luser.messagingpage.graphicalchat.SendChatMessagesEvent;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.messagingpage.graphicalchat.models.GraphicalChat;
import models.messaging.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ChatsPage extends LUserPanel implements ServerAnswerInterpreter {
    private final JButton okButton;
    private GraphicalChat graphicalChat;
    private boolean chatPageShowed;
    private final MessagingPage messagingPage;
    ArrayList<ArrayList<String>> chatsInf1;

    public ChatsPage(LUserPanel previousPage, ArrayList<ArrayList<String>> chatsInf, MessagingPage messagingPage) {
        super(previousPage, previousPage.getMainPanel(), previousPage.getOwnerId());
        this.setBounds(0, 0, 800, 740);
        this.messagingPage = messagingPage;
        chatsInf1 = chatsInf;

        this.setLayout(null);

        String [] chatsInfo = new String[chatsInf.size()];

        for (int i = 0; i < chatsInf.size(); i++) {
            if (this.getOwnerId() == Integer.parseInt(chatsInf.get(i).get(3))){
                chatsInfo[i] = chatsInf.get(i).get(2);
            } else {
                chatsInfo[i] = chatsInf.get(i).get(1);
            }
        }

        JList<String> jList = new JList<String>(chatsInfo);
        jList.setVisibleRowCount(4);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setFont(new Font("Arial", Font.PLAIN, 25));
        jList.repaint();

        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setBounds(0, 0, 200, chatsInfo.length*40);

        scrollPane.repaint();

        okButton = new JButton("OK");
        okButton.setBounds(200, 0, 100, 100);
        okButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestChatInfo(chatsInf.get(jList.getSelectedIndex()).get(0));
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        this.add(okButton);
        this.setBackground(Color.GREEN);
        this.add(scrollPane);
        this.repaint();
        this.revalidate();

    }

    public void setChatPageShowed(boolean chatPageShowed) {
        this.chatPageShowed = chatPageShowed;
    }

    private void requestChatInfo(String token){
        try {
            sendClientEvent(new RequestChatInfoEvent(token), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showChatPage(Chat chat){
        chatPageShowed = true;
        try {
            sendClientEvent(new ActivateWaintingThreadEvent(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.graphicalChat = new GraphicalChat(this, chat, this);
        this.graphicalChat.mShow();
    }

    public void openChatByToken(String token){
        requestChatInfo(token);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        interpretServerAnswer(logicalEvent);
    }

    @Override
    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        if (serverEvent.getClass() == SendChatMessagesEvent.class){
            if (chatPageShowed){
                graphicalChat.receiveServerEvent(serverEvent);
            }
        }else
        if (serverEvent.getClass() == SendChatInfoServerEvent.class){
            showChatPage(((SendChatInfoServerEvent)serverEvent).getChat());
        }
    }

    public void openGroupPage(String token){
        this.back();
        messagingPage.openGroupPage(token);
    }

    public void openChatPage(String token){
        try {
            for (ArrayList<String> arrayList: chatsInf1){
                if (arrayList.get(1).equals(token) || arrayList.get(2).equals(token)){
                    sendClientEvent(new RequestChatInfoEvent(arrayList.get(0)), true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openBotChatPage(String token){
        this.back();
        messagingPage.openBotChatPage(token);
    }
}
