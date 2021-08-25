package models.tweet;


import models.user.User;

public class Comment extends Tweet {
    private static final long serialVersionUID = 1234567L;

    public Comment(String text, User user, String tags) {
        super(text, user, tags);
    }

    public Comment(){

    }



}
