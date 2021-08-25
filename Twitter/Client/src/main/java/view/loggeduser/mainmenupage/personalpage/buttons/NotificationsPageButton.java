package view.loggeduser.mainmenupage.personalpage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.NotificationsPage;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;

import java.awt.event.MouseEvent;

public class NotificationsPageButton extends PersonalPageButton{
    public NotificationsPageButton(Config buttonConfig, PersonalPage personalPage) {
        super(buttonConfig, personalPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        personalPage.setNotificationsPage(new NotificationsPage(personalPage));
        personalPage.getNotificationsPage().mShow();
    }
}
