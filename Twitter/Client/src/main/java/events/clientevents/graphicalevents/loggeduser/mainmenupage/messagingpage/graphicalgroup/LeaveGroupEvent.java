package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup;

import models.messaging.Group;

public class LeaveGroupEvent extends GraphicalGroupEvent{
    private final Group group;

    public LeaveGroupEvent(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
