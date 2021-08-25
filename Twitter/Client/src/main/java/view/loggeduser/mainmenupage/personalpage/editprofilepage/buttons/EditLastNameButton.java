package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditLastNameEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditLastNameButton extends EditProfilePageButton{
    public EditLastNameButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String newLastName = JOptionPane.showInputDialog("Enter New Lastname:");
        try {
            editProfilePage.sendClientEvent(new EditLastNameEvent(newLastName), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
