package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents;

import javafx.scene.control.Hyperlink;
import models.other.hyperlink.HyperLink;

public class OpenChatHyperLinkEvent extends HyperLinkEvent{
    public OpenChatHyperLinkEvent(HyperLink hyperlink) {
        super(hyperlink);
    }
}
