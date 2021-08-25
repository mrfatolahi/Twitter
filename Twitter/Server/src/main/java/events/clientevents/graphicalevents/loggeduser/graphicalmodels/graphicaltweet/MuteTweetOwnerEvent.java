package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;

import models.tweet.Tweet;

public class MuteTweetOwnerEvent extends GraphicalTweetEvent {
    private final Tweet tweet;

    public MuteTweetOwnerEvent(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }
}
