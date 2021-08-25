package models.tweet;


import java.io.Serializable;
import java.util.ArrayList;

public class Tag implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private String name;
    private ArrayList<Tweet> tweets;
    private int numberOfUses;

    public Tag(String name) {
        this.name = name;
        numberOfUses = 0;
    }

    public Tag() {
    }

    public ArrayList<Tweet> getTweets(){
        return tweets;
    }

    public int getNumberOfUses(){
        return numberOfUses;
    }

    public String getName(){
        return name;
    }

}
