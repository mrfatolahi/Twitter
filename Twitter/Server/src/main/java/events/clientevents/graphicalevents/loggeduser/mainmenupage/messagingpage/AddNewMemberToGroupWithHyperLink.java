package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage;

public class AddNewMemberToGroupWithHyperLink extends MessagingPageEvent{
    private final int groupOwnerId;
    private final String groupToken;

    public AddNewMemberToGroupWithHyperLink(int groupOwnerId, String groupToken) {
        this.groupOwnerId = groupOwnerId;
        this.groupToken = groupToken;
    }

    public int getGroupOwnerId() {
        return groupOwnerId;
    }

    public String getGroupToken() {
        return groupToken;
    }
}
