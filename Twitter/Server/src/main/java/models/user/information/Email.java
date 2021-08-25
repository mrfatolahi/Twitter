package models.user.information;


import models.user.User;

public class Email {
    private String text;
    private int userId;
    private boolean visibility;

    public Email() {
    }

    public Email(User user, String text) {
        this.text = text;
        this.userId = user.getId();
        this.visibility = false;
    }

    public Email(int userId, String text) {
        this.text = text;
        this.userId = userId;
    }

    public boolean isVisibility() {
        return visibility;
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

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String toString(){
        return this.text;
    }
}
