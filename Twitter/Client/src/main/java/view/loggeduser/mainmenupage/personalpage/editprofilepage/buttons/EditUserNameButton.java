package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditUserNameEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditUserNameButton extends EditLastNameButton{
    public EditUserNameButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String newUserName = JOptionPane.showInputDialog("Enter New Username:");
        try {
            editProfilePage.sendClientEvent(new EditUserNameEvent(newUserName), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
