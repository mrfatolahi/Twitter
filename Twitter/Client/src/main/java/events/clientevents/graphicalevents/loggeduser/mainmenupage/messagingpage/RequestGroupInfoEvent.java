package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage;

public class RequestGroupInfoEvent extends MessagingPageEvent{
    private final String token;

    public RequestGroupInfoEvent(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
