package view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.RequestSentRequestsEvent;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.RequestsPage;

import java.awt.event.MouseEvent;

public class SentRequestsButton extends RequestsPageButton{
    public SentRequestsButton(Config buttonConfig, RequestsPage requestsPage) {
        super(buttonConfig, requestsPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            requestsPage.sendClientEvent(new RequestSentRequestsEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
