package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage;

import java.util.ArrayList;

public class SpreadEvent extends MessagingPageEvent{
    private final String text;
    private final ArrayList<String> membersUsernames;

    public SpreadEvent(String text, ArrayList<String> membersUsernames) {
        this.text = text;
        this.membersUsernames = membersUsernames;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getMembersUsernames() {
        return membersUsernames;
    }
}
