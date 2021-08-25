package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;

import models.tweet.Tweet;

public class NewLikeEvent extends GraphicalTweetEvent {
    private final Tweet tweet;

    public NewLikeEvent(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }
}
