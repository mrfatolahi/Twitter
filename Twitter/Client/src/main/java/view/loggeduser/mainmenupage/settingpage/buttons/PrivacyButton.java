package view.loggeduser.mainmenupage.settingpage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.settingpage.SettingPage;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.PrivacySettingPage;

import java.awt.event.MouseEvent;

public class PrivacyButton extends SettingPageButton{
    public PrivacyButton(Config buttonConfig, SettingPage settingPage) {
        super(buttonConfig, settingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        settingPage.setPrivacySettingPage(new PrivacySettingPage(settingPage));
        settingPage.getPrivacySettingPage().mShow();
    }
}
