package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage;

public class RequestMakingNewChatEvent extends MessagingPageEvent {
    private final String userToChatUsername;

    public RequestMakingNewChatEvent(String userToChatUsername) {
        this.userToChatUsername = userToChatUsername;
    }

    public String getUserToChatUsername() {
        return userToChatUsername;
    }
}
