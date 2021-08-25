package events.serverevents.luser.messagingpage.graphicalgroup;

import models.messaging.ChatMassage;

import java.util.ArrayList;

public class SendGroupMessagesEvent extends GraphicalGroupServerEvent {
    private final ArrayList<ChatMassage> chatMassages;

    public SendGroupMessagesEvent(ArrayList<ChatMassage> chatMassages) {
        this.chatMassages = chatMassages;
    }

    public ArrayList<ChatMassage> getChatMassages() {
        return chatMassages;
    }
}
