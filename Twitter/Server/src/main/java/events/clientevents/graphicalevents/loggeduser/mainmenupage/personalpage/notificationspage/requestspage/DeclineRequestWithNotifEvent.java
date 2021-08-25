package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.PersonalPageEvent;
import models.other.FollowRequest;

public class DeclineRequestWithNotifEvent extends PersonalPageEvent {
    private final FollowRequest followRequest;

    public DeclineRequestWithNotifEvent(FollowRequest followRequest) {
        this.followRequest = followRequest;
    }

    public FollowRequest getFollowRequest() {
        return followRequest;
    }
}
