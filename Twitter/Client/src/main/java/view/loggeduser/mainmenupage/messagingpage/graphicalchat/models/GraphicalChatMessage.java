package view.loggeduser.mainmenupage.messagingpage.graphicalchat.models;

import config.Config;
import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import view.mainclasses.GraphicalAgent;
import models.messaging.ChatMassage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicalChatMessage extends JPanel implements EventManager {
    private final EventManager eventManager;
    private final ChatMassage chatMassage;
    private final int userId;

    public GraphicalChatMessage(EventManager eventManager, ChatMassage chatMassage) {
        this.eventManager = eventManager;
        this.chatMassage = chatMassage;
        this.userId = chatMassage.getUserId();
        Color background = Color.WHITE;

        switch (chatMassage.getChatMessageStatus()){
            case HAVE_NOT_RECORDED_IN_SERVER:
                this.setBackground(Color.LIGHT_GRAY);
                background = Color.LIGHT_GRAY;
                break;
            case RECEIVER_HAVE_RECEIVED_THE_MESSAGE:
                this.setBackground(Color.MAGENTA);
                background = Color.MAGENTA;
                break;
            case RECEIVER_HAVE_NOT_SEEN_THE_MESSAGE:
                this.setBackground(Color.YELLOW);
                background = Color.YELLOW;
                break;
            case RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET:
                this.setBackground(Color.CYAN);
                background = Color.CYAN;
                break;

        }
        this.setLayout(null);
        this.setMaximumSize(new Dimension(200, 300));
        TextArea textArea = new TextArea(chatMassage.getText(), 0, 0, TextArea.SCROLLBARS_NONE);
        textArea.setEditable(false);
        textArea.setBounds(0, 0, 200,100);
        textArea.repaint();
        this.add(textArea);
        String imagePath = chatMassage.getImagePath();
        JLabel imageLabel = new JLabel();
        if (imagePath == null || imagePath.equals("")){
            imagePath = Config.getConfig("ImagesPro").getProperty("NoImage");
        }else {
            imageLabel.setIcon(GraphicalAgent.reSizeImage(200, 150, imagePath));
        }
        imageLabel.setBounds(0, 100, 200, 150);
        this.add(imageLabel);
        JLabel dateAndTimeCreatedLabel = new JLabel(chatMassage.getTimeCreated().toString());
        dateAndTimeCreatedLabel.setBounds(0, 250, 100, 50);
        dateAndTimeCreatedLabel.setBackground(Color.RED);
        dateAndTimeCreatedLabel.repaint();
        this.add(dateAndTimeCreatedLabel);

        if (chatMassage.getHyperlink() != null){
            JButton hyperLinkButton = new JButton();
            hyperLinkButton.setText(chatMassage.getHyperlink().getText());
            hyperLinkButton.setForeground(Color.BLUE.darker());
            hyperLinkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            hyperLinkButton.addMouseListener(new HyperLinkMouseListener(this));
            hyperLinkButton.setBorderPainted(false);
            hyperLinkButton.setBackground(background);
            hyperLinkButton.setBounds(100, 250, 100, 50);
            this.add(hyperLinkButton);
        }

        this.repaint();
    }

    public static GraphicalChatMessage buildGraphicalChatMessage(EventManager eventManager, ChatMassage chatMassage){
        return new GraphicalChatMessage(eventManager, chatMassage);
    }

    public static ArrayList<GraphicalChatMessage> buildGraphicalChatMessages(EventManager eventManager, ArrayList<ChatMassage> chatMassages){
        ArrayList<GraphicalChatMessage> graphicalChatMessages = new ArrayList<>();

        for (ChatMassage chatMassage : chatMassages){
            graphicalChatMessages.add(buildGraphicalChatMessage(eventManager, chatMassage));
        }

        return graphicalChatMessages;
    }


    public EventManager getGraphicEventManager() {
        return eventManager;
    }

    public ChatMassage getChatMassage() {
        return chatMassage;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        eventManager.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {

    }
}
