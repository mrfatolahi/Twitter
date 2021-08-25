package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;

public class EditEmailEvent extends EditProfilePageEvent {
    private final String newEmail;

    public EditEmailEvent(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }
}
