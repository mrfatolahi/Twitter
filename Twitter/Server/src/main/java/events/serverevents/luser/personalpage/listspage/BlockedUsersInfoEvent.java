package events.serverevents.luser.personalpage.listspage;

import events.serverevents.luser.LUserServerEvent;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;

import java.util.ArrayList;

public class BlockedUsersInfoEvent extends ListsPageServerEvent {
    private final ArrayList<UserInfoServerEvent> blockedUsers;

    public BlockedUsersInfoEvent(ArrayList<UserInfoServerEvent> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public ArrayList<UserInfoServerEvent> getBlockedUsers() {
        return blockedUsers;
    }
}
