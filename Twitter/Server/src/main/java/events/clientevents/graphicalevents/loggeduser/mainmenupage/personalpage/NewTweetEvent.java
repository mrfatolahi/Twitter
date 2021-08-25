package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage;


public class NewTweetEvent extends PersonalPageEvent {
    private final String text;
    private final String tags;
    private final String imagePath;

    public NewTweetEvent(String text, String tags, String imagePath) {
        this.text = text;
        this.tags = tags;
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public String getTags() {
        return tags;
    }

    public String getImagePath() {
        return imagePath;
    }
}
