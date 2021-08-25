package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;

public class EditBioEvent extends EditProfilePageEvent {
    private final String newBio;

    public EditBioEvent(String newBio) {
        this.newBio = newBio;
    }

    public String getNewBio() {
        return newBio;
    }
}
