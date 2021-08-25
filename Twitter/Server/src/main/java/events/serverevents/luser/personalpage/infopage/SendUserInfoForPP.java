package events.serverevents.luser.personalpage.infopage;


import events.serverevents.luser.LUserServerEvent;

public class SendUserInfoForPP extends InfoPageServerEvent {
    private final String name;
    private final String lastname;
    private final String username;
    private final String birthdate;
    private final String email;
    private final String phonenumber;
    private final String bio;
    private final String profileImagePath;

    public SendUserInfoForPP(
            String name, String lastname, String username,
            String birthdate, String email, String phonenumber,
            String bio, String profileImagePath
    ) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.birthdate = birthdate;
        this.email = email;
        this.phonenumber = phonenumber;
        this.bio = bio;
        this.profileImagePath = profileImagePath;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getBio() {
        return bio;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }
}
