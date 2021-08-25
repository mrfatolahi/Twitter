package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;

public class EditNameEvent extends EditProfilePageEvent {
    private final String newName;

    public EditNameEvent(String newName) {
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
