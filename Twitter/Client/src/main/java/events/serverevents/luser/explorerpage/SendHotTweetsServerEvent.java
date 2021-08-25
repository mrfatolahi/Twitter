package events.serverevents.luser.explorerpage;

import models.tweet.Tweet;

import java.util.ArrayList;

public class SendHotTweetsServerEvent extends ExplorerPageServerEvent {
    private static final long serialVersionUID = 1234567L;
    private final ArrayList<Tweet> hotTweets;

    public SendHotTweetsServerEvent(ArrayList<Tweet> hotTweets) {
        this.hotTweets = hotTweets;
    }

    public ArrayList<Tweet> getHotTweets() {
        return hotTweets;
    }
}
