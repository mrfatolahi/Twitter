package view.loggeduser.mainmenupage.personalpage.NotificationsPage.requestspage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.AcceptRequestEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.DeclineRequestWithNotifEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.DeclineRequestWithOutNotifEvent;
import events.clientevents.main.InterruptWaitingThread;
import view.loggeduser.LUserPanel;
import models.other.FollowRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ReceivedRequestsPage extends LUserPanel {
    private final RequestsPage requestsPage;

    public ReceivedRequestsPage(RequestsPage requestsPage, ArrayList<FollowRequest> receivedFollowRequests) {
        super(requestsPage, requestsPage.getMainPanel(), requestsPage.getOwnerId());
        this.requestsPage = requestsPage;

        this.setLayout(null);

        this.setBackground(new Color(250, 250, 250));

        this.setBounds(0, 0, 800, 720);

        int height = 0;

        for (FollowRequest followRequest : receivedFollowRequests){
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(0, height, 400, 30);
            JLabel label = new JLabel(followRequest.getReceiverUsername()+"     Status: "+ followRequest.isAccepted());
            label.setBounds(0, height, 200, 30);
            panel.add(label);

            JButton acceptButton = new JButton("Accept");
            acceptButton.setBounds(200, height, 100, 30);
            acceptButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        sendClientEvent(new AcceptRequestEvent(followRequest), false);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Done!");
//                    mShow();
                }
                public void mousePressed(MouseEvent e){}
                public void mouseReleased(MouseEvent e){}
                public void mouseEntered(MouseEvent e){}
                public void mouseExited(MouseEvent e){}
            });
            panel.add(acceptButton);

            JButton declineButton = new JButton("decline");
            declineButton.setBounds(300, height, 100, 30);
            declineButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String [] options = {"With Notif", "Without Notif"};
                    int optionNumber = JOptionPane.showOptionDialog(
                            null,
                            "Decline request...",
                            "Options",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            -1
                    );

                    switch (optionNumber){
                        case 0:
                            try {
                                sendClientEvent(new DeclineRequestWithNotifEvent(followRequest), false);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                sendClientEvent(new DeclineRequestWithOutNotifEvent(followRequest), false);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Done!");
//                    mShow();
                }
                public void mousePressed(MouseEvent e){}
                public void mouseReleased(MouseEvent e){}
                public void mouseEntered(MouseEvent e){}
                public void mouseExited(MouseEvent e){}
            });
            panel.add(declineButton);
            panel.repaint();
            this.add(panel);
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
            requestsPage.setReceivedRequestsPageShowed(false);
        }
        super.back();
    }
}
