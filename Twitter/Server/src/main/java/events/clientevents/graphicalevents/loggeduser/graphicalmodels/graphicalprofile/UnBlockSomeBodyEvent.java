package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile;


public class UnBlockSomeBodyEvent extends GraphicalProfileEvent {
    private final int userToUnBlockId;

    public UnBlockSomeBodyEvent(int userToUnBlockId) {
        this.userToUnBlockId = userToUnBlockId;
    }

    public int getUserToUnBlockId() {
        return userToUnBlockId;
    }
}
