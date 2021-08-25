package view.loggeduser.mainmenupage.settingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.DeleteAccountEvent;
import view.loggeduser.mainmenupage.settingpage.SettingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class DeleteAccountButton extends SettingPageButton{
    public DeleteAccountButton(Config buttonConfig, SettingPage settingPage) {
        super(buttonConfig, settingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "Program will be closed.");
        try {
            settingPage.sendClientEvent(new DeleteAccountEvent(), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
