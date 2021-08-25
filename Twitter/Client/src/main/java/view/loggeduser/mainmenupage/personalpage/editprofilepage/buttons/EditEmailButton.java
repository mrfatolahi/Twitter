package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditEmailEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditEmailButton extends EditProfilePageButton {
    public EditEmailButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String newEmail = JOptionPane.showInputDialog("Enter New Email:");
        try {
            editProfilePage.sendClientEvent(new EditEmailEvent(newEmail), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
