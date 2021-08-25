package view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.RequestReceivedRequestsEvent;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.RequestsPage;

import java.awt.event.MouseEvent;

public class ReceivedRequestsButton extends RequestsPageButton{
    public ReceivedRequestsButton(Config buttonConfig, RequestsPage requestsPage) {
        super(buttonConfig, requestsPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            requestsPage.sendClientEvent(new RequestReceivedRequestsEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
