package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.*;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;
import models.user.information.*;
import saversandloaders.savers.UserSaver;

import java.io.IOException;

public class EditProfilePageEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public EditProfilePageEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <F extends EditProfilePageEvent> void interpreteEditProfilePageEvent(F editProfilePageEvent) throws Exception {
        if (editProfilePageEvent.getClass() == EditNameEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setName(new Name(mainInterPreter.getlUserLogicalAgent().getUser(), ((EditNameEvent) editProfilePageEvent).getNewName()));
        } else
        if (editProfilePageEvent.getClass() == EditLastNameEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setLastname(new LastName(mainInterPreter.getlUserLogicalAgent().getUser(), ((EditLastNameEvent) editProfilePageEvent).getNewLastName()));
        } else
        if (editProfilePageEvent.getClass() == EditUserNameEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setUsername(new UserName(mainInterPreter.getlUserLogicalAgent().getUser(), ((EditUserNameEvent) editProfilePageEvent).getNewUserName()));
        } else
        if (editProfilePageEvent.getClass() == EditPasswordEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setPassword(new Password(mainInterPreter.getlUserLogicalAgent().getUser(), ((EditPasswordEvent) editProfilePageEvent).getNewPassword()));
        } else
        if (editProfilePageEvent.getClass() == EditBirthDateEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setBirthdate(((EditBirthDateEvent) editProfilePageEvent).getNewBirthDate());
        } else
        if (editProfilePageEvent.getClass() == EditEmailEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setEmail(new Email(mainInterPreter.getlUserLogicalAgent().getUser(), ((EditEmailEvent) editProfilePageEvent).getNewEmail()));
        } else
        if (editProfilePageEvent.getClass() == EditPhoneNumberEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setPhonenumber(new Phonenumber(((EditPhoneNumberEvent) editProfilePageEvent).getMainPart(), ((EditPhoneNumberEvent) editProfilePageEvent).getCountryCode(), mainInterPreter.getlUserLogicalAgent().getUser()));
        } else
        if (editProfilePageEvent.getClass() == EditBioEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setBio(new Bio(mainInterPreter.getlUserLogicalAgent().getUser(), ((EditBioEvent) editProfilePageEvent).getNewBio()));
        } else
        if (editProfilePageEvent.getClass() == EditProfileImageEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setProfileImagePath(((EditProfileImageEvent) editProfilePageEvent).getImagePath());
        }
        UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteEditProfilePageEvent((EditProfilePageEvent) clientEvent);
    }
}
