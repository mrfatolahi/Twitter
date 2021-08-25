package view.loggeduser.mainmenupage.settingpage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.settingpage.SettingPage;

import java.awt.event.MouseEvent;

public class LogOutButton extends SettingPageButton{
    public LogOutButton(Config buttonConfig, SettingPage settingPage) {
        super(buttonConfig, settingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        settingPage.getMainMenuPage().getlUserGraphicalAgent().openAWellComePage();
    }
}
