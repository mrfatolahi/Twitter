package events.serverevents.luser.messagingpage;

import models.messaging.Chat;

public class DeclareNewChatMadeEvent extends MessagingPageServerEvent {
    private final Chat chat;

    public DeclareNewChatMadeEvent(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }
}
