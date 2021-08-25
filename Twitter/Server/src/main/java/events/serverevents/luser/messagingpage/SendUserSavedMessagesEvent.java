package events.serverevents.luser.messagingpage;

import models.tweet.Tweet;

import java.util.ArrayList;

public class SendUserSavedMessagesEvent extends MessagingPageServerEvent {
    private final ArrayList<Tweet> tweets;

    public SendUserSavedMessagesEvent(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }
}
