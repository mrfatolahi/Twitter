package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditPhoneNumberEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditPhoneNumberButton extends EditProfilePageButton{
    public EditPhoneNumberButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String countryCode = JOptionPane.showInputDialog("Enter Country Code:");
        String mainPart = JOptionPane.showInputDialog("Enter Main Part (Without 0)");
        try {
            editProfilePage.sendClientEvent(new EditPhoneNumberEvent(countryCode, mainPart), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
