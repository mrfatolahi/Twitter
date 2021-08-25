package view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditPasswordEvent;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.PrivacySettingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ChangePasswordButton extends PrivacySettingPageButton{
    public ChangePasswordButton(Config buttonConfig, PrivacySettingPage privacySettingPage) {
        super(buttonConfig, privacySettingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String password = JOptionPane.showInputDialog("Enter New Password:");
        try {
            privacySettingPage.sendClientEvent(new EditPasswordEvent(password), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
