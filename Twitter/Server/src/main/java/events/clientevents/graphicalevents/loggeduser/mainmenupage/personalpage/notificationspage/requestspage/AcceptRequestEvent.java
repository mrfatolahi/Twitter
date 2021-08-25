package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.PersonalPageEvent;
import models.other.FollowRequest;

public class AcceptRequestEvent extends PersonalPageEvent {
    private final FollowRequest followRequest;

    public AcceptRequestEvent(FollowRequest followRequest) {
        this.followRequest = followRequest;
    }

    public FollowRequest getFollowRequest() {
        return followRequest;
    }
}
