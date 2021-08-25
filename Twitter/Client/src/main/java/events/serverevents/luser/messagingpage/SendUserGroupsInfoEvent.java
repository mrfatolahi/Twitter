package events.serverevents.luser.messagingpage;

import java.util.ArrayList;

public class SendUserGroupsInfoEvent extends MessagingPageServerEvent {
    private final ArrayList<ArrayList<String>> groupsInfo;

    public SendUserGroupsInfoEvent(ArrayList<ArrayList<String>> groupsInfo) {
        this.groupsInfo = groupsInfo;
    }

    public ArrayList<ArrayList<String>> getGroupsInfo() {
        return groupsInfo;
    }
}
