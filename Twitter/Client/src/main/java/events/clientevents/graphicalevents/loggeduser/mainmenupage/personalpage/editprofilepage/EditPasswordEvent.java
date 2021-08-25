package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;

public class EditPasswordEvent extends EditProfilePageEvent {
    private final String newPassword;

    public EditPasswordEvent(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
