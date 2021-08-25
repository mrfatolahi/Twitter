package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;

import models.tweet.Tweet;

public class RetweetEvent extends GraphicalTweetEvent {
    private final Tweet tweet;
    private final String text;
    private final String tags;
    private final String imagePath;

    public RetweetEvent(Tweet tweet, String text, String tags, String imagePath) {
        this.tweet = tweet;
        this.text = text;
        this.tags = tags;
        this.imagePath = imagePath;
    }

    public Tweet getTweet() {
        return tweet;
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
