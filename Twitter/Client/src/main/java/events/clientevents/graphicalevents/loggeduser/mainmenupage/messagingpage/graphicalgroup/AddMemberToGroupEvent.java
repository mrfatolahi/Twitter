package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup;

import models.messaging.Group;

import java.util.ArrayList;

public class AddMemberToGroupEvent extends GraphicalGroupEvent {
    private final ArrayList<String> newMembersUsernames;
    private final Group group;

    public AddMemberToGroupEvent(ArrayList<String> newMembersUsernames, Group group) {
        this.newMembersUsernames = newMembersUsernames;
        this.group = group;
    }

    public ArrayList<String> getNewMembersUsernames() {
        return newMembersUsernames;
    }

    public Group getGroup() {
        return group;
    }
}
