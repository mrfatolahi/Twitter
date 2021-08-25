package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.MessagingPageEvent;

public class RequestGroupInfoEvent extends MessagingPageEvent {
    private final String token;

    public RequestGroupInfoEvent(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
