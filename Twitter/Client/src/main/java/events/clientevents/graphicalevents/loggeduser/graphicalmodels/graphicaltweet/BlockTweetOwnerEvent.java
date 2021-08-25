package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;

import models.tweet.Tweet;

public class BlockTweetOwnerEvent extends GraphicalTweetEvent {
    private final Tweet tweet;

    public BlockTweetOwnerEvent(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }
}
