package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditBirthDateEvent;
import models.time.MDate;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EditBirthDateButton extends EditProfilePageButton{

    public EditBirthDateButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter Year:"));
        int month = Integer.parseInt(JOptionPane.showInputDialog("Enter Month:"));
        int day = Integer.parseInt(JOptionPane.showInputDialog("Enter Day:"));
        try {
            editProfilePage.sendClientEvent(new EditBirthDateEvent(new MDate(year, month, day)), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
