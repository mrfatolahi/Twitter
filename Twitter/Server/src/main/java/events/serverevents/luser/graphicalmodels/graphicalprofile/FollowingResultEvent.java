package events.serverevents.luser.graphicalmodels.graphicalprofile;


public class FollowingResultEvent extends GraphicalProfileServerEvent {
    private final FollowingResult followingResult;

    public FollowingResultEvent(FollowingResult followingResult) {
        this.followingResult = followingResult;
    }

    public FollowingResult getFollowingResult() {
        return followingResult;
    }
}
