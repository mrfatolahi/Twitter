package view.loggeduser.mainmenupage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.loggeduser.mainmenupage.explorerpage.ExplorerPage;

import java.awt.event.MouseEvent;

public class ExplorerButton extends MenuButton {
    public ExplorerButton(Config buttonConfig, MainMenuPage mainMenuPage) {
        super(buttonConfig, mainMenuPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mainMenuPage.setExpelorerPage(new ExplorerPage(mainMenuPage));
        mainMenuPage.getExpelorerPage().mShow();
    }
}
