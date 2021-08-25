package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;


import models.tweet.Tweet;

public class NewCommentEvent extends GraphicalTweetEvent {
    private final Tweet tweet;
    private final String text;


    public NewCommentEvent(Tweet tweet, String text) {
        this.tweet = tweet;
        this.text = text;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public String getText() {
        return text;
    }
}
