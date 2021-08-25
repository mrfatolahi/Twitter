package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.MessagingPageEvent;
import models.other.hyperlink.HyperLink;

public abstract class HyperLinkEvent extends MessagingPageEvent {
    private final HyperLink hyperlink;

    public HyperLinkEvent(HyperLink hyperlink) {
        this.hyperlink = hyperlink;
    }

    public HyperLink getHyperlink() {
        return hyperlink;
    }
}
