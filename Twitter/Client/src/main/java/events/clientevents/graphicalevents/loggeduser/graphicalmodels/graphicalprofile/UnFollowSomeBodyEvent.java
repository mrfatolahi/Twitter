package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile;



public class UnFollowSomeBodyEvent extends GraphicalProfileEvent {
    private final int userToUnFollowId;

    public UnFollowSomeBodyEvent(int userToUnFollowId) {
        this.userToUnFollowId = userToUnFollowId;
    }

    public int getUserToUnFollowId() {
        return userToUnFollowId;
    }
}
