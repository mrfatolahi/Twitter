package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;

public class EditUserNameEvent extends EditProfilePageEvent {
    private final String newUserName;

    public EditUserNameEvent(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getNewUserName() {
        return newUserName;
    }
}
