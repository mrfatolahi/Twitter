package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditNameEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditNameButton extends EditProfilePageButton{
    public EditNameButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String newName = JOptionPane.showInputDialog("Enter New Name:");
        try {
            editProfilePage.sendClientEvent(new EditNameEvent(newName), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
