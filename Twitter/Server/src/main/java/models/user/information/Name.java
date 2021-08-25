package models.user.information;


import models.user.User;

public class Name {
    private String text;
    private int userId;

    public Name() {
    }

    public Name(User user, String text) {
        this.text = text;
        this.userId = user.getId();
    }

    public Name(int userId, String text) {
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

    public void validationCheck() throws Exception {
        Exception InvalidNameException = new Exception();
        if(text.length() == 1){throw InvalidNameException;}
        for (int i = 0 ; i < text.length() ; i++){
            if(text.charAt(i) == '0'  || text.charAt(i) == '1'  || text.charAt(i) == '2'  || text.charAt(i) == '3'  ||
                    text.charAt(i) == '4'  || text.charAt(i) == '5'  || text.charAt(i) == '6'  || text.charAt(i) == '7'  ||
                    text.charAt(i) == '8'  || text.charAt(i) == '9'){
                throw InvalidNameException;
            }
        }
    }

    public String toString(){
        return this.text;
    }
}
