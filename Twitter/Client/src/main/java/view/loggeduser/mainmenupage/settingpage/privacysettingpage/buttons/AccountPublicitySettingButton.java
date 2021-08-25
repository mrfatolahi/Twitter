package view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.MakeAccountPrivateEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.MakeAccountPublicEvent;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.PrivacySettingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class AccountPublicitySettingButton extends PrivacySettingPageButton{
    public AccountPublicitySettingButton(Config buttonConfig, PrivacySettingPage privacySettingPage) {
        super(buttonConfig, privacySettingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String [] options = {"Public", "Private"};
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
                    privacySettingPage.sendClientEvent(new MakeAccountPublicEvent(), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case 1:
                try {
                    privacySettingPage.sendClientEvent(new MakeAccountPrivateEvent(), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
        }
    }
}
