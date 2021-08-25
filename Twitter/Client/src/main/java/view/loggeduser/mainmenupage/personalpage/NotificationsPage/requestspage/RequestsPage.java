package view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.RequestReceivedRequestsEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.RequestSentRequestsEvent;
import events.clientevents.main.ActivateWaintingThreadEvent;
import events.serverevents.luser.personalpage.notificationspage.SendReceivedRequestsEvent;
import events.serverevents.luser.personalpage.notificationspage.SendSentRequestsEvent;
import events.serverevents.luser.personalpage.notificationspage.UpdateReceivedRequestsPage;
import events.serverevents.luser.personalpage.notificationspage.UpdateSentRequestsPage;
import events.serverevents.main.ServerEvent;
import models.other.FollowRequest;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.NotificationsPage;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.buttons.ReceivedRequestsButton;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage.buttons.SentRequestsButton;

import java.awt.*;

public class RequestsPage extends LUserPanel {
    private final SentRequestsButton sentRequestsButton;
    private final ReceivedRequestsButton receivedRequestsButton;
    private boolean sentRequestsPageShowed;
    private boolean receivedRequestsPageShowed;

    public RequestsPage(NotificationsPage notificationsPage) {
        super(notificationsPage, notificationsPage.getMainPanel(), notificationsPage.getOwnerId());

        sentRequestsPageShowed = false;
        receivedRequestsPageShowed = false;
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

        sentRequestsButton = new SentRequestsButton(Config.getConfig("MenuButtonsPro"), this);
        sentRequestsButton.setText("Sent Requests");
        sentRequestsButton.setBounds(250, 120, 300, 50);
        this.add(sentRequestsButton);

        receivedRequestsButton = new ReceivedRequestsButton(Config.getConfig("MenuButtonsPro"), this);
        receivedRequestsButton.setText("Received Requests");
        receivedRequestsButton.setBounds(250, 190, 300, 50);
        this.add(receivedRequestsButton);

        this.repaint();
    }

    public void setSentRequestsPageShowed(boolean sentRequestsPageShowed) {
        this.sentRequestsPageShowed = sentRequestsPageShowed;
    }

    public void setReceivedRequestsPageShowed(boolean receivedRequestsPageShowed) {
        this.receivedRequestsPageShowed = receivedRequestsPageShowed;
    }

    private void showReceivedRequestsPage(SendReceivedRequestsEvent sendReceivedRequestsEvent){
        receivedRequestsPageShowed = true;
        try {
            sendClientEvent(new ActivateWaintingThreadEvent(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        (new ReceivedRequestsPage(this, (sendReceivedRequestsEvent).getFollowRequests())).mShow();
    }

    private void showSentRequestsPage(SendSentRequestsEvent sendSentRequestsEvent){
        for (FollowRequest followRequest : sendSentRequestsEvent.getFollowRequests()){
            System.out.println("followRequest.getText() = " + followRequest.getText());
        }
        sentRequestsPageShowed = true;
        try {
            sendClientEvent(new ActivateWaintingThreadEvent(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        (new SentRequestsPage(this, (sendSentRequestsEvent).getFollowRequests())).mShow();
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        interpretServerAnswer(logicalEvent);
    }

    @Override
    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        if ((serverEvent.getClass() == SendSentRequestsEvent.class)){
            showSentRequestsPage((SendSentRequestsEvent) serverEvent);
        } else
        if ((serverEvent.getClass() == SendReceivedRequestsEvent.class)){
            showReceivedRequestsPage((SendReceivedRequestsEvent) serverEvent);
        } else
        if ((serverEvent.getClass() == UpdateReceivedRequestsPage.class)){
            if (receivedRequestsPageShowed){
                this.sendClientEvent(new RequestReceivedRequestsEvent(), true);
            }
        } else
        if ((serverEvent.getClass() == UpdateSentRequestsPage.class)){
            if (sentRequestsPageShowed){
                this.sendClientEvent(new RequestSentRequestsEvent(), true);
            }
        }
    }
}
