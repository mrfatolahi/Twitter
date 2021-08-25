package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.MessagingPageEvent;
import models.messaging.Chat;

public class UpdateOfflineChatInfo extends MessagingPageEvent {
    private final Chat chat;

    public UpdateOfflineChatInfo(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }
}
