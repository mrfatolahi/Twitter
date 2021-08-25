package view.loggeduser.mainmenupage.personalpage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import java.awt.event.MouseEvent;

public class EditProfilePageButton extends PersonalPageButton{
    public EditProfilePageButton(Config buttonConfig, PersonalPage personalPage) {
        super(buttonConfig, personalPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        personalPage.setEditProfilePage(new EditProfilePage(personalPage));
        personalPage.getEditProfilePage().mShow();
    }
}
