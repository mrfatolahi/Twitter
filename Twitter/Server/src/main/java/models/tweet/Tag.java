package models.tweet;


import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.ArrayList;

public class Tag implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private String name;
    private int numberOfUses;

    public Tag(String name) {
        this.name = name;
        numberOfUses = 0;
    }

    public Tag() {
    }

    public int getNumberOfUses(){
        return numberOfUses;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfUses(int numberOfUses) {
        this.numberOfUses = numberOfUses;
    }
}
