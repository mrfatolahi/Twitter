package view.loggeduser.mainmenupage.personalpage.NotificationsPage;

import config.Config;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.buttons.RequestsPageButton;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.buttons.SystemMassagesPageButton;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.RequestsPage;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;

import java.awt.*;

public class NotificationsPage extends LUserPanel implements ServerAnswerInterpreter {
    private final RequestsPageButton requestsPageButton;
    private final SystemMassagesPageButton systemMassagesPageButton;
    private RequestsPage requestsPage;
    public NotificationsPage(PersonalPage personalPage) {
        super(personalPage, personalPage.getMainPanel(), personalPage.getOwnerId());

        this.setLayout(null);

        Config config = Config.getConfig("Frame");
        this.setBackground(new Color(
                Integer.parseInt(config.getProperty("colorR")),
                Integer.parseInt(config.getProperty("colorG")),
                Integer.parseInt(config.getProperty("colorB"))
        ));

        this.setBounds(
                Integer.parseInt(config.getProperty("x")),
                Integer.parseInt(config.getProperty("y")),
                Integer.parseInt(config.getProperty("width")),
                Integer.parseInt(config.getProperty("height"))
        );

        requestsPageButton = new RequestsPageButton(Config.getConfig("MenuButtonsPro"), this);
        requestsPageButton.setText("Requests");
        requestsPageButton.setBounds(250, 120, 300, 50);
        this.add(requestsPageButton);

        systemMassagesPageButton = new SystemMassagesPageButton(Config.getConfig("MenuButtonsPro"), this);
        systemMassagesPageButton.setText("System Massages");
        systemMassagesPageButton.setBounds(250, 190, 300, 50);
        this.add(systemMassagesPageButton);
    }

    public RequestsPageButton getRequestsPageButton() {
        return requestsPageButton;
    }

    public RequestsPage getRequestsPage() {
        return requestsPage;
    }

    public void setRequestsPage(RequestsPage requestsPage) {
        this.requestsPage = requestsPage;
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        requestsPage.receiveServerEvent(logicalEvent);
    }
}
