package events.serverevents.luser.personalpage.tweetspage;

import models.tweet.Tweet;

import java.util.ArrayList;

public class SendUserTweetsEvent extends TweetsPageServerEvent {
    private final ArrayList<Tweet> tweets;

    public SendUserTweetsEvent(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }
}
