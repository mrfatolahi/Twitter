package models.tweet;


public class Comment extends Tweet{
    private static final long serialVersionUID = 1234567L;

    public Comment(String text, int userId, String tags) {
        super(text, userId, tags);
    }

    public Comment(){

    }



}
