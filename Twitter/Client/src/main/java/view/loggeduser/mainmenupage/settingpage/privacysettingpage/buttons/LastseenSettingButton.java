package view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.PrivacySettingPage;

import java.awt.event.MouseEvent;

public class LastseenSettingButton extends PrivacySettingPageButton{
    public LastseenSettingButton(Config buttonConfig, PrivacySettingPage privacySettingPage) {
        super(buttonConfig, privacySettingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
}
