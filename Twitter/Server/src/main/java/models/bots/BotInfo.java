package models.bots;

import events.serverevents.luser.explorerpage.UserInfoServerEvent;
import models.tweet.Tweet;

import java.util.ArrayList;

public abstract class BotInfo {
    protected final String username;
    private final ArrayList<Tweet> tweets;
    protected final ArrayList<Integer> followersId;

    public BotInfo(String username) {
        this.followersId = new ArrayList<>();
        this.tweets = new ArrayList<>();
        this.username = username;
    }

    public BotInfo(String username, ArrayList<Integer> followersId) {
        this.followersId = followersId;
        this.tweets = new ArrayList<>();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Integer> getFollowersId() {
        return followersId;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public UserInfoServerEvent generateBotInfoAsEvent(){
        return null;
    }
}
