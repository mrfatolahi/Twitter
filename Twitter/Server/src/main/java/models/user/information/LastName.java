package models.user.information;


import models.user.User;

public class LastName {
    private String text;
    private int userId;

    public LastName() {
    }

    public LastName(User user, String text) {
        this.text = text;
        this.userId = user.getId();
    }

    public LastName(int userId, String text) {
        this.text = text;
        this.userId = userId;
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

    public String toString(){
        return this.text;
    }
}
