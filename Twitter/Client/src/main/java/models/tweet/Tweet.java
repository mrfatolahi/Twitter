package models.tweet;

import javafx.scene.control.Hyperlink;
import models.time.MDateTime;

import java.io.Serializable;
import java.util.ArrayList;

public class Tweet implements Serializable {
    private static final long serialVersionUID = 1234567L;
    protected String text;
    protected String imagePath;
    protected int userId;
    protected String userUsername;
    protected String userProfileImagePath;
    protected int Id;
    protected int timesReported;
    protected MDateTime DateAndTimeCreated;
    protected ArrayList<Comment> comments;
    protected ArrayList<Like> likes;
    protected ArrayList<Tag> tags;
    protected ArrayList<Retweet> retweets;
    protected Hyperlink hyperlink;

    public Tweet() {
    }

    public Tweet(String text, int userId, String tags, String imagePath, String userUsername, String userProfileImagePath) {
        this.text = text;
        this.userId = userId;
        this.userUsername = userUsername;
        this.userProfileImagePath = userProfileImagePath;
        this.imagePath = imagePath;
        this.timesReported = 0;
        this.tags = GiveTags(tags);
        this.DateAndTimeCreated = MDateTime.now();
        this.comments = new ArrayList<Comment>();
        this.likes = new ArrayList<Like>();
        this.retweets = new ArrayList<Retweet>();
        this.Id = 0;
    }

    public Tweet(String text, int userId, String tags) {
    }

    public ArrayList<Tag> GiveTags(String Tags){
        ArrayList<Tag> tags = new ArrayList<Tag>();
        String tagname = "";
        /**
         * this part separate tags and add them to their list
         */
        for(int i = 0 ; i < Tags.length() ; i++){
            if(Tags.charAt(i) == ' '){
                String s = "";
                tags.add(new Tag(tagname));
                tagname = "";
                continue;
            }
            tagname = tagname + Tags.charAt(i);
        }
        return tags;
    }

    public int getTimesReported() {
        return timesReported;
    }

    public void setTimesReported(int timesReported) {
        this.timesReported = timesReported;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public MDateTime getDateAndTimeCreated() {
        return DateAndTimeCreated;
    }

    public void setDateAndTimeCreated(MDateTime dateAndTimeCreated) {
        DateAndTimeCreated = dateAndTimeCreated;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Retweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(ArrayList<Retweet> retweets) {
        this.retweets = retweets;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserProfileImagePath() {
        return userProfileImagePath;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public void setUserProfileImagePath(String userProfileImagePath) {
        this.userProfileImagePath = userProfileImagePath;
    }

    public Hyperlink getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(Hyperlink hyperlink) {
        this.hyperlink = hyperlink;
    }
}
