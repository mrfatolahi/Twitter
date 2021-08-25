package events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage;

public class ERequestUserInfoEvent extends ExplorerPageEvent{
    private final String username;

    public ERequestUserInfoEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
