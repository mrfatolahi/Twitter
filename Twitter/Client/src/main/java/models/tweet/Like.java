package models.tweet;


import models.time.MDateTime;

import java.io.Serializable;

public class Like implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private int userId;
    private int tweetId;
    private MDateTime DateAndTimeCreated;

    public Like(int userId, int tweetId, MDateTime dateAndTimeCreated) {
        this.userId = userId;
        this.tweetId = tweetId;
        DateAndTimeCreated = dateAndTimeCreated;
    }

    public Like() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public void setDateAndTimeCreated(MDateTime dateAndTimeCreated) {
        DateAndTimeCreated = dateAndTimeCreated;
    }

    public int getTweetId() {
        return tweetId;
    }

    public MDateTime getDateAndTimeCreated() {
        return DateAndTimeCreated;
    }
}
