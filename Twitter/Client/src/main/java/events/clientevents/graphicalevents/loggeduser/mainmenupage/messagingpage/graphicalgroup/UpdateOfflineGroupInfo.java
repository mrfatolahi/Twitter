package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup;


import models.messaging.Group;

public class UpdateOfflineGroupInfo extends GraphicalGroupEvent{
    private final Group group;

    public UpdateOfflineGroupInfo(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
