package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;

public class EditProfileImageEvent extends EditProfilePageEvent {
    private final String imagePath;

    public EditProfileImageEvent(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
