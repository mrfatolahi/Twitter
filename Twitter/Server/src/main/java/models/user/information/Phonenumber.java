package models.user.information;


import models.user.User;

public class Phonenumber {
    private int userId;
    private String CountryCode;
    private String MainPart;
    private boolean visibility;

    public Phonenumber() {
    }


    public Phonenumber(String countryCode, String mainPart, User user) {
        this.userId = user.getId();
        this.MainPart = mainPart;
        this.CountryCode = countryCode;
        this.visibility= false;
    }

    public Phonenumber(String countryCode, String mainPart, int userId) {
        this.MainPart = mainPart;
        this.CountryCode = countryCode;
        this.visibility= false;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getMainPart() {
        return MainPart;
    }

    public void setMainPart(String mainPart) {
        MainPart = mainPart;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String toString(){
        return this.CountryCode+MainPart;
    }

    public String GetText(){
        return this.CountryCode+MainPart;
    }
}
