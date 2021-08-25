package events.serverevents.luser.messagingpage;

import models.messaging.Chat;

public class SendChatInfoServerEvent extends MessagingPageServerEvent{
    private final Chat chat;

    public SendChatInfoServerEvent(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }
}
