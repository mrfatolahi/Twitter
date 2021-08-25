package models.user.information;


import models.user.User;

public class Password extends UserInformation{
    private String text;

    public Password() {
    }


    public Password(User user, String text) throws Exception {
        super(user.getId());
        this.text = text;
    }

    public Password(int userId, String text) throws Exception {
        super(userId);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        return this.text;
    }


}
