package events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet;

import models.tweet.Tweet;

public class ReportTweetEvent extends GraphicalTweetEvent {
    private final Tweet tweet;

    public ReportTweetEvent(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }
}
