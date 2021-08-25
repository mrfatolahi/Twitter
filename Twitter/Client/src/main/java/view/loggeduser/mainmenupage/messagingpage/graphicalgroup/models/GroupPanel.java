package view.loggeduser.mainmenupage.messagingpage.graphicalgroup.models;

import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import view.loggeduser.mainmenupage.messagingpage.graphicalchat.models.GraphicalChatMessage;
import models.messaging.ChatMassage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GroupPanel extends JPanel implements EventManager {
    private final EventManager eventManager;
    private ArrayList<ChatMassage> chatMassages;
    private ArrayList<GraphicalChatMessage> graphicalChatMessages;

    public GroupPanel(EventManager eventManager, ArrayList<ChatMassage> chatMassages) {
        this.eventManager = eventManager;
        this.chatMassages = chatMassages;
        this.initialize();
    }

    public void initialize(){
        this.graphicalChatMessages = GraphicalChatMessage.buildGraphicalChatMessages(this, chatMassages);

        this.setLayout(null);
        this.setBackground(Color.GREEN);

        this.setPreferredSize(new Dimension(400,(graphicalChatMessages.size() * 400)));

        int height = 100;

        for (GraphicalChatMessage graphicalChatMessage : graphicalChatMessages){
            if (graphicalChatMessage.getUserId() == 1){
                graphicalChatMessage.setBounds(10, height, 200, 300);
                this.add(graphicalChatMessage);
                height += 400;
                this.repaint();
            } else {
                graphicalChatMessage.setBounds(190, height, 200, 300);
                this.add(graphicalChatMessage);
                height += 400;
                this.repaint();
            }
        }

    }


    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        eventManager.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {

    }
}
