package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;


import models.tweet.Tweet;

public class AddTweetToSavedMessages extends GraphicalTweetEvent {
    private final int viewerId;
    private final Tweet tweet;

    public AddTweetToSavedMessages(int viewerId, Tweet tweet) {
        this.viewerId = viewerId;
        this.tweet = tweet;
    }

    public int getViewerId() {
        return viewerId;
    }

    public Tweet getTweet() {
        return tweet;
    }
}
