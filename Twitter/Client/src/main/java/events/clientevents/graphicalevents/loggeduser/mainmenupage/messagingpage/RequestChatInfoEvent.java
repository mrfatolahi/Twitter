package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage;

public class RequestChatInfoEvent extends MessagingPageEvent {
    private final String token;

    public RequestChatInfoEvent(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
