package models.tweet;

import models.user.User;

public class Retweet extends Tweet{
    private static final long serialVersionUID = 1234567L;
    private int tweetId;

    public Retweet() {
    }

    public Retweet(int tweetid, String text, User owner, String tags, String imagePath) {
        super(text, owner.getId(), tags, imagePath, owner.getUsername().getText(), owner.getProfileImagePath());
        this.tweetId = tweetid;
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }
}
