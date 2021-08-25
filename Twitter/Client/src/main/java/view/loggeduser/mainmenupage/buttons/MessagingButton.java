package view.loggeduser.mainmenupage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;

import java.awt.event.MouseEvent;

public class MessagingButton extends MenuButton {
    public MessagingButton(Config buttonConfig, MainMenuPage mainMenuPage) {
        super(buttonConfig, mainMenuPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mainMenuPage.setMessagingPage(new MessagingPage(mainMenuPage));
        mainMenuPage.getMessagingPage().mShow();
    }
}
