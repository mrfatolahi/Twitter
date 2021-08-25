package events.serverevents.luser.timelinepage;

import models.tweet.Tweet;

import java.util.ArrayList;

public class SendTimeLineTweetsEvent extends TimeLinePageServerEvent {
    private final ArrayList<Tweet> tweets;

    public SendTimeLineTweetsEvent(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }
}
