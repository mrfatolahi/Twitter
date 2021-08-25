package events.serverevents.luser.personalpage.listspage;


import events.serverevents.luser.explorerpage.UserInfoServerEvent;

import java.util.ArrayList;

public class FollowingsInfoEvent extends ListsPageServerEvent {
    private final ArrayList<UserInfoServerEvent> followings;

    public FollowingsInfoEvent(ArrayList<UserInfoServerEvent> followings) {
        this.followings = followings;
    }

    public ArrayList<UserInfoServerEvent> getFollowings() {
        return followings;
    }
}
