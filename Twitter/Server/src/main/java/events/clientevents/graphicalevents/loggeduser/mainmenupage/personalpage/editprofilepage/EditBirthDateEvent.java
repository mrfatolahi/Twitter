package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;


import models.time.MDate;

public class EditBirthDateEvent extends EditProfilePageEvent {
    private final MDate newBirthDate;

    public EditBirthDateEvent(MDate newBirthDate) {
        this.newBirthDate = newBirthDate;
    }

    public MDate getNewBirthDate() {
        return newBirthDate;
    }
}
