package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;


import models.tweet.Tweet;

public class TweetForwardEvent extends GraphicalTweetEvent {
    private final Tweet tweet;
    private final String receiverUsername;

    public TweetForwardEvent(Tweet tweet, String receiverUsername) {
        this.tweet = tweet;
        this.receiverUsername = receiverUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public Tweet getTweet() {
        return tweet;
    }
}
