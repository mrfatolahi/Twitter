package events.serverevents.luser.messagingpage.graphicalchat;

import models.messaging.ChatMassage;

import java.util.ArrayList;

public class SendChatMessagesEvent extends GraphicalChatServerEvent {
    private final ArrayList<ChatMassage> chatMassages;

    public SendChatMessagesEvent(ArrayList<ChatMassage> chatMassages) {
        this.chatMassages = chatMassages;
    }

    public ArrayList<ChatMassage> getChatMassages() {
        return chatMassages;
    }
}
