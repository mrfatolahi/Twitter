package view.loggeduser.mainmenupage.messagingpage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestChatInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestGroupInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserChatsWithBotsInfoEvent;
import events.clientevents.main.ActivateWaintingThreadEvent;
import events.serverevents.luser.messagingpage.SendGroupInfoServerEvent;
import events.serverevents.luser.messagingpage.graphicalchat.SendChatMessagesEvent;
import events.serverevents.luser.messagingpage.graphicalgroup.GraphicalGroupDoubleBackEvent;
import events.serverevents.luser.messagingpage.graphicalgroup.SendGroupMessagesEvent;
import events.serverevents.main.ServerEvent;
import models.messaging.Group;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.messagingpage.graphicalgroup.models.GraphicalGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GroupsPage extends LUserPanel{
    private boolean groupPageShowed;
    private final JButton okButton;
    private GraphicalGroup graphicalGroup;
    private final MessagingPage messagingPage;

    public GroupsPage(LUserPanel previousPage, ArrayList<ArrayList<String>> groupsInfo, MessagingPage messagingPage) {
        super(previousPage, previousPage.getMainPanel(), previousPage.getOwnerId());
        this.messagingPage = messagingPage;
        this.setBounds(0, 0, 800, 720);

        this.setLayout(null);

        String [] chatsInfo = new String[groupsInfo.size()];

        for (int i = 0; i < groupsInfo.size(); i++) {
            chatsInfo[i] = groupsInfo.get(i).get(1);
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
                requestGroupInfo(groupsInfo.get(jList.getSelectedIndex()).get(0));
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

    private void requestGroupInfo(String token){
        try {
            sendClientEvent(new RequestGroupInfoEvent(token), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGroupPageShowed(boolean groupPageShowed) {
        this.groupPageShowed = groupPageShowed;
    }

    public void showGroupPage(Group group){
        groupPageShowed = true;
        try {
            sendClientEvent(new ActivateWaintingThreadEvent(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.graphicalGroup = new GraphicalGroup(this, group, this);
        this.graphicalGroup.mShow();
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        interpretServerAnswer(logicalEvent);
    }

    @Override
    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        if (serverEvent.getClass() == SendGroupMessagesEvent.class){
            if (groupPageShowed){
                this.graphicalGroup.receiveServerEvent(serverEvent);
            }
        } else
        if (serverEvent.getClass() == SendGroupInfoServerEvent.class){
            showGroupPage(((SendGroupInfoServerEvent) serverEvent).getGroup());
        } else
        if (serverEvent.getClass() == GraphicalGroupDoubleBackEvent.class){
            if (groupPageShowed){
                graphicalGroup.back();
            }
            this.back();
        }
    }

    public void openGroupByToken(String token){
        requestGroupInfo(token);
    }

    public void openGroupPage(String token){
        try {
            requestGroupInfo(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openChatPage(String token){
        this.back();
        messagingPage.openChatPage(token);
    }

    public void openBotChatPage(String token){

        this.back();
        messagingPage.openBotChatPage(token);
    }
}
