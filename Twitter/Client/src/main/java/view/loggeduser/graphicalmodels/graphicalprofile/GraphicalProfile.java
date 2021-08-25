package view.loggeduser.graphicalmodels.graphicalprofile;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile.BlockSomeBodyEvent;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile.FollowSomeBodyEvent;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile.UnBlockSomeBodyEvent;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile.UnFollowSomeBodyEvent;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;
import events.serverevents.main.ServerEvent;
import view.mainclasses.GraphicalAgent;
import view.loggeduser.LUserPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GraphicalProfile extends LUserPanel {
    private final int userId;
    private final JButton followButton;
    private final JButton unFollowButton;
    private JButton blockButton;
    private JButton unBlockButton;
    private JButton backButton;
    private JLabel profilePictureLabel;
    private JLabel numberOfFollowersLabel;
    private JLabel numberOfFollowingsLabel;
    private JLabel nameAndLastNameLabel;
    private JLabel usernameLabel;
    private JLabel lastSeenLabel;
    private JLabel privacyStatusLabel;
    private JTextArea bioArea;



    public GraphicalProfile(LUserPanel previousPage, UserInfoServerEvent userInfoLogicalEvent) {
        super(previousPage, previousPage.getMainPanel(), previousPage.getOwnerId());
        userId = userInfoLogicalEvent.getOwnerId();

        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(0, 0, 600, 360);

        this.followButton = new JButton("follow");
        followButton.setBounds(420, 10, 90, 40);
        followButton.setFocusPainted(false);
        followButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    sendClientEvent(new FollowSomeBodyEvent(userId), true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        });
        this.add(followButton);

        this.unFollowButton = new JButton("unfollow");
        unFollowButton.setBounds(510, 10, 90, 40);
        unFollowButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    sendClientEvent(new UnFollowSomeBodyEvent(userId), false);
                    JOptionPane.showMessageDialog(null,"Done!");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        });
        this.add(unFollowButton);

        if (!userInfoLogicalEvent.isBot()){
            this.blockButton = new JButton("block");
            blockButton.setBounds(420, 50, 90, 40);
            blockButton.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        sendClientEvent(new BlockSomeBodyEvent(userId), false);
                        JOptionPane.showMessageDialog(null, "Done!");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }
            });
            this.add(blockButton);
        }

        if (!userInfoLogicalEvent.isBot()){
            this.unBlockButton = new JButton("unblock");
            unBlockButton.setBounds(510, 50, 90, 40);
            unBlockButton.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        sendClientEvent(new UnBlockSomeBodyEvent(userId), false);
                        JOptionPane.showMessageDialog(null, "Done!");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }
            });
            this.add(unBlockButton);
        }


        if (!userInfoLogicalEvent.isBot()){
            this.profilePictureLabel = new JLabel();
            this.profilePictureLabel.setIcon(GraphicalAgent.reSizeImage(100, 100, userInfoLogicalEvent.getProfileImagePath()));
            this.profilePictureLabel.setBounds(0, 10, 100, 100);
            this.profilePictureLabel.repaint();
            this.add(profilePictureLabel);
        }

        numberOfFollowersLabel = new JLabel(userInfoLogicalEvent.getNumberOfFollowers()+"  Followers");
        numberOfFollowersLabel.setBounds(110, 250, 70, 20);
        this.add(numberOfFollowersLabel);

        if (!userInfoLogicalEvent.isBot()){
            numberOfFollowingsLabel = new JLabel(userInfoLogicalEvent.getNumberOfFollowings() + " Followings");
            numberOfFollowingsLabel.setBounds(410, 250, 70, 20);
            this.add(numberOfFollowingsLabel);
        }

        nameAndLastNameLabel = new JLabel(userInfoLogicalEvent.getName()+" "+userInfoLogicalEvent.getLastName());
        nameAndLastNameLabel.setBounds(130, 20, 300, 30);
        this.add(nameAndLastNameLabel);

        usernameLabel = new JLabel("@"+userInfoLogicalEvent.getUsername());
        usernameLabel.setBounds(130, 70, 300, 30);
        this.add(usernameLabel);

        bioArea = new JTextArea(userInfoLogicalEvent.getBio());
        bioArea.setEditable(false);
        bioArea.setBackground(new Color(250, 250, 250));
        bioArea.setBounds(0, 120, 600, 100);
        this.add(bioArea);

        backButton = new JButton("B");
        backButton.setBounds(0, 340, 50, 20);
        backButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    back();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        });
        this.add(backButton);

        if (!userInfoLogicalEvent.isBot()){
            lastSeenLabel = new JLabel("Lastseen: " + userInfoLogicalEvent.getLastseen());
            lastSeenLabel.setBounds(70, 300, 180, 20);
            this.add(lastSeenLabel);

            privacyStatusLabel = new JLabel("Is Private? " + userInfoLogicalEvent.getPrivacyStatus());
            privacyStatusLabel.setBounds(410, 300, 150, 20);
            this.add(privacyStatusLabel);
        }

        this.repaint();
        this.revalidate();
    }



    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws IOException {

    }
}
