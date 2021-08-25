package view.loggeduser.mainmenupage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.loggeduser.mainmenupage.settingpage.SettingPage;

import java.awt.event.MouseEvent;

public class SettingButton extends MenuButton {
    public SettingButton(Config buttonConfig, MainMenuPage mainMenuPage) {
        super(buttonConfig, mainMenuPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mainMenuPage.setSettingPage(new SettingPage(mainMenuPage));
        mainMenuPage.getSettingPage().mShow();
    }
}
