package view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.ActiveAccountEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.DeActiveAccountEvent;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.PrivacySettingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class AccountActivenessSettingButton extends PrivacySettingPageButton{
    public AccountActivenessSettingButton(Config buttonConfig, PrivacySettingPage privacySettingPage) {
        super(buttonConfig, privacySettingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String [] options = {"Active", "DeActive"};
        int optionNumber = JOptionPane.showOptionDialog(
                null,
                "Make my account...",
                "Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                -1
        );

        switch (optionNumber){
            case 0:
                try {
                    privacySettingPage.sendClientEvent(new ActiveAccountEvent(), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case 1:
                try {
                    privacySettingPage.sendClientEvent(new DeActiveAccountEvent(), false);
                    System.exit(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
        }
    }
}
