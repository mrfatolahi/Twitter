package events.serverevents.luser.personalpage.notificationspage;


import models.other.FollowRequest;

import java.util.ArrayList;

public class SendSentRequestsEvent extends NotificationsPageServerEvent {
    private final ArrayList<FollowRequest> followRequests;

    public SendSentRequestsEvent(ArrayList<FollowRequest> followRequests) {
        this.followRequests = followRequests;
    }

    public ArrayList<FollowRequest> getFollowRequests() {
        return followRequests;
    }
}
