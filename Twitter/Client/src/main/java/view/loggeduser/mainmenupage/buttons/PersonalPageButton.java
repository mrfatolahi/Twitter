package view.loggeduser.mainmenupage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PersonalPageButton extends MenuButton {
    public PersonalPageButton(Config buttonConfig, MainMenuPage mainMenuPage) {
        super(buttonConfig, mainMenuPage);
        this.setMaximumSize(new Dimension(300, 100));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            mainMenuPage.setPersonalPage(new PersonalPage(mainMenuPage));
            mainMenuPage.getPersonalPage().mShow();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
