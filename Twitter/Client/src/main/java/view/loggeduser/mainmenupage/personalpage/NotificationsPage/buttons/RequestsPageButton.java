package view.loggeduser.mainmenupage.personalpage.NotificationsPage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.NotificationsPage;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.RequestsPage;

import java.awt.event.MouseEvent;

public class RequestsPageButton extends NotificationsPageButton{
    public RequestsPageButton(Config buttonConfig, NotificationsPage notificationsPage) {
        super(buttonConfig, notificationsPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        notificationsPage.setRequestsPage(new RequestsPage(notificationsPage));
        notificationsPage.getRequestsPage().mShow();
    }
}
