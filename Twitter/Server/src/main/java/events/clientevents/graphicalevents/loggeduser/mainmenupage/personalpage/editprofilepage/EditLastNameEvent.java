package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;


public class EditLastNameEvent extends EditProfilePageEvent {
    private final String newLastName;

    public EditLastNameEvent(String newLastName) {
        this.newLastName = newLastName;
    }

    public String getNewLastName() {
        return newLastName;
    }
}
