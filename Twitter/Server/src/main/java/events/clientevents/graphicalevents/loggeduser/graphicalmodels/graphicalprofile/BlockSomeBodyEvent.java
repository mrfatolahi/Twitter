package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile;


public class BlockSomeBodyEvent extends GraphicalProfileEvent {
    private final int userToBlockId;

    public BlockSomeBodyEvent(int userToBlockId) {
        this.userToBlockId = userToBlockId;
    }

    public int getUserToBlockId() {
        return userToBlockId;
    }
}
