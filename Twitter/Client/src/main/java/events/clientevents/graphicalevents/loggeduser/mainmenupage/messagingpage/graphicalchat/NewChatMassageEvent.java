package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat;

import models.other.hyperlink.HyperLinkType;

public class NewChatMassageEvent extends GraphicalChatEvent {
    private final int receiverId;
    private final String text;
    private final String imagePath;
    private boolean hasAnswer;
    private final String hyperLinkText;
    private final HyperLinkType hyperLinkType;
    private final String hyperLinkDestination;

    public NewChatMassageEvent(int receiverId, String text, String imagePath) {
        this.receiverId = receiverId;
        this.text = text;
        this.imagePath = imagePath;
        this.hasAnswer = true;
        this.hyperLinkType = null;
        this.hyperLinkDestination = null;
        this.hyperLinkText = null;
    }

    public NewChatMassageEvent(int receiverId, String text, String imagePath, String hyperLinkText, HyperLinkType hyperLinkType, String hyperLinkDestination) {
        this.receiverId = receiverId;
        this.text = text;
        this.imagePath = imagePath;
        this.hasAnswer = true;
        this.hyperLinkText = hyperLinkText;
        this.hyperLinkType = hyperLinkType;
        this.hyperLinkDestination = hyperLinkDestination;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isHasAnswer() {
        return hasAnswer;
    }

    public void setHasAnswer(boolean hasAnswer) {
        this.hasAnswer = hasAnswer;
    }

    public HyperLinkType getHyperLinkType() {
        return hyperLinkType;
    }

    public String getHyperLinkDestination() {
        return hyperLinkDestination;
    }

    public String getHyperLinkText() {
        return hyperLinkText;
    }
}
