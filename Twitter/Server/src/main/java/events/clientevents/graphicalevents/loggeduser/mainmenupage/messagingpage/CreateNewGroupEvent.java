package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage;

import java.util.ArrayList;

public class CreateNewGroupEvent extends MessagingPageEvent{
    private final String groupName;
    private final ArrayList<String> membersUsernames;

    public CreateNewGroupEvent(String groupName, ArrayList<String> membersUsernames) {
        this.groupName = groupName;
        this.membersUsernames = membersUsernames;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<String> getMembersUsernames() {
        return membersUsernames;
    }
}
