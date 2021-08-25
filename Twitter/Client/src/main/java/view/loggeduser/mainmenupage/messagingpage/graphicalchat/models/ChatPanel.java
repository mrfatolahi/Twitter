package view.loggeduser.mainmenupage.messagingpage.graphicalchat.models;

import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import models.messaging.ChatMassage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatPanel extends JPanel implements EventManager {
    private final EventManager eventManager;
    private ArrayList<ChatMassage> chatMassages;
    private ArrayList<GraphicalChatMessage> graphicalChatMessages;
    private final int viewerId;

    public ChatPanel(EventManager eventManager, ArrayList<ChatMassage> chatMassages, int viewerId) {
        this.eventManager = eventManager;
        this.chatMassages = chatMassages;
        this.viewerId = viewerId;
        this.initialize();
    }

    public void initialize(){
        for (ChatMassage chatMassage : chatMassages){
            System.out.println("chatMassage.getHyperlink() = " + chatMassage.getHyperlink());
        }
        this.graphicalChatMessages = GraphicalChatMessage.buildGraphicalChatMessages(this, chatMassages);

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        this.setPreferredSize(new Dimension(400,(graphicalChatMessages.size() * 400)));

        int height = 100;

        for (GraphicalChatMessage graphicalChatMessage : graphicalChatMessages){
            if (graphicalChatMessage.getUserId() == viewerId){
                graphicalChatMessage.setBounds(190, height, 200, 300);
                this.add(graphicalChatMessage);
                height += 400;
                this.repaint();
            } else {
                graphicalChatMessage.setBounds(10, height, 200, 300);
                this.add(graphicalChatMessage);
                height += 400;
                this.repaint();
            }
        }

    }

    public void setChatMassages(ArrayList<ChatMassage> chatMassages) {
        this.chatMassages = chatMassages;
    }

    public ArrayList<ChatMassage> getChatMassages() {
        return chatMassages;
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        eventManager.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {

    }
}
