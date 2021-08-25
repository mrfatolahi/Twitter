package events.serverevents.luser.messagingpage;


import models.messaging.Group;

public class SendGroupInfoServerEvent extends MessagingPageServerEvent{
    private final Group group;

    public SendGroupInfoServerEvent(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
