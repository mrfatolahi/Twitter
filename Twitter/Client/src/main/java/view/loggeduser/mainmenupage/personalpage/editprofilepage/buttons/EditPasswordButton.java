package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditPasswordEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditPasswordButton extends EditProfilePageButton{
    public EditPasswordButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String newPassword = JOptionPane.showInputDialog("Enter New Password:");
        try {
            editProfilePage.sendClientEvent(new EditPasswordEvent(newPassword), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
