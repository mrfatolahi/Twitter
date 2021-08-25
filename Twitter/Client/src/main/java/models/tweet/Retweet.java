package models.tweet;


public class Retweet extends Tweet{
    private static final long serialVersionUID = 1234567L;
    private int tweetId;

    public Retweet() {
    }

    public Retweet(int tweetid, String text, int ownerId, String tags, String imagePath, String userUsername, String userProfileImagePath) {
        super(text, ownerId, tags, imagePath, userUsername, userProfileImagePath);
        this.tweetId = tweetid;
    }
}
