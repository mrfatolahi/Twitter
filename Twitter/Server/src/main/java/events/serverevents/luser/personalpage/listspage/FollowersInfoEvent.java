package events.serverevents.luser.personalpage.listspage;

import events.serverevents.luser.LUserServerEvent;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;

import java.util.ArrayList;

public class FollowersInfoEvent extends ListsPageServerEvent {
    private final ArrayList<UserInfoServerEvent> followers;

    public FollowersInfoEvent(ArrayList<UserInfoServerEvent> followers) {
        this.followers = followers;
    }

    public ArrayList<UserInfoServerEvent> getFollowers() {
        return followers;
    }
}
