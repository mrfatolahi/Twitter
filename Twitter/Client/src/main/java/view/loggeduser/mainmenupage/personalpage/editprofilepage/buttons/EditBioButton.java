package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditBioEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditBioButton extends EditPhoneNumberButton{

    public EditBioButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String newBio = JOptionPane.showInputDialog("Enter New Bio:");
        try {
            editProfilePage.sendClientEvent(new EditBioEvent(newBio), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
