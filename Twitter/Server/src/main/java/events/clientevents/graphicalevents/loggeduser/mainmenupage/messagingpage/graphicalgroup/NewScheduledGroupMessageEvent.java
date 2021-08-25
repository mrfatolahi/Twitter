package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup;

import java.util.ArrayList;

public class NewScheduledGroupMessageEvent extends GraphicalGroupEvent{
    private final ArrayList<Integer> receiversId;
    private final String text;
    private final String imagePath;
    private boolean hasAnswer;
    private final int hour;
    private final int minute;

    public NewScheduledGroupMessageEvent(ArrayList<Integer> receiversId, String text, String imagePath, boolean hasAnswer, int hour, int minute) {
        this.receiversId = receiversId;
        this.text = text;
        this.imagePath = imagePath;
        this.hasAnswer = hasAnswer;
        this.hour = hour;
        this.minute = minute;
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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
