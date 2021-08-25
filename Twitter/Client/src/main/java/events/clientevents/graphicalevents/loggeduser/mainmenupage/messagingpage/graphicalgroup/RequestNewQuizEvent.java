package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup;

import java.util.ArrayList;

public class RequestNewQuizEvent extends GraphicalGroupEvent{
    private final ArrayList<Integer> groupMembersIds;

    public RequestNewQuizEvent(ArrayList<Integer> groupMembersIds) {
        this.groupMembersIds = groupMembersIds;
    }

    public ArrayList<Integer> getGroupMembersIds() {
        return groupMembersIds;
    }
}
