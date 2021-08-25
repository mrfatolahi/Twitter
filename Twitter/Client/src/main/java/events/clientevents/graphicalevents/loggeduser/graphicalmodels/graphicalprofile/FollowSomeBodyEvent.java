package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile;


public class FollowSomeBodyEvent extends GraphicalProfileEvent{
    private final int userToFollowId;

    public FollowSomeBodyEvent(int userToFollowId) {
        this.userToFollowId = userToFollowId;
    }

    public int getUserToFollowId() {
        return userToFollowId;
    }
}
