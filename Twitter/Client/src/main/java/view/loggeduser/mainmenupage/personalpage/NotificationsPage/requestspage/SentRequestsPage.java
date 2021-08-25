package view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage;

import events.clientevents.main.InterruptWaitingThread;
import view.loggeduser.LUserPanel;
import models.other.FollowRequest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SentRequestsPage extends LUserPanel {
    private final RequestsPage requestsPage;

    public SentRequestsPage(RequestsPage requestsPage, ArrayList<FollowRequest> sentFollowRequests) {
        super(requestsPage, requestsPage.getMainPanel(), requestsPage.getOwnerId());
        this.requestsPage = requestsPage;
        this.setLayout(null);

        this.setBackground(new Color(250, 250, 250));

        this.setBounds(0, 0, 800, 720);

        int height = 0;

        for (FollowRequest followRequest : sentFollowRequests){
            JLabel label = new JLabel(followRequest.getReceiverUsername()+"     Status: "+ followRequest.isAccepted());
            label.setBounds(0, height, 400, 30);
            this.add(label);
            height += 30;
        }

        this.repaint();
    }

    @Override
    public void back() {
        try {
//            System.out.println("Thread.activeCount()111 = " + Thread.activeCount());
            sendClientEvent(new InterruptWaitingThread(), false);
//            System.out.println("Thread.activeCount()222 = " + Thread.activeCount());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (requestsPage != null){
            requestsPage.setSentRequestsPageShowed(false);
        }
        super.back();
    }
}
