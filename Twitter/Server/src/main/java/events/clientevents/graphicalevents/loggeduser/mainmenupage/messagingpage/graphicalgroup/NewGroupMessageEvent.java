package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup;

import models.other.hyperlink.HyperLinkType;

import java.util.ArrayList;

public class NewGroupMessageEvent extends GraphicalGroupEvent {
    private final ArrayList<Integer> receiversId;
    private final String text;
    private final String imagePath;
    private boolean hasAnswer;
    private final String hyperLinkText;
    private final HyperLinkType hyperLinkType;
    private final String hyperLinkDestination;

    public NewGroupMessageEvent(ArrayList<Integer> receiversId, String text, String imagePath) {
        this.receiversId = receiversId;
        this.text = text;
        this.imagePath = imagePath;
        this.hyperLinkType = null;
        this.hasAnswer = true;
        this.hyperLinkDestination = null;
        this.hyperLinkText = null;
    }

    public NewGroupMessageEvent(ArrayList<Integer> receiversId, String text, String imagePath, String hyperLinkText, HyperLinkType hyperLinkType, String hyperLinkDestination) {
        this.receiversId = receiversId;
        this.text = text;
        this.imagePath = imagePath;
        this.hyperLinkText = hyperLinkText;
        this.hyperLinkType = hyperLinkType;
        this.hyperLinkDestination = hyperLinkDestination;
    }

    public ArrayList<Integer> getReceiversId() {
        return receiversId;
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
