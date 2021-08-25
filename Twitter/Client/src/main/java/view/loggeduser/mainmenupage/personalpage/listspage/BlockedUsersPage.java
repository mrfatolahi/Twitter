package view.loggeduser.mainmenupage.personalpage.listspage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestBlockedUsersEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestFollowingsEvent;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicalprofile.GraphicalProfile;
import view.mainclasses.Updatable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BlockedUsersPage extends LUserPanel implements Updatable {
    private final ArrayList<UserInfoServerEvent> blockedUsers;
    private Thread updaterThread;

    public BlockedUsersPage(LUserPanel previousPage, ArrayList<UserInfoServerEvent> blockedUsers) {
        super(previousPage, previousPage.getMainPanel(), previousPage.getOwnerId());
        this.blockedUsers = blockedUsers;
        this.initialize();
    }

    public void initialize(){
        this.setLayout(null);
        this.setBounds(0, 0, 800, 720);
        JPanel mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(600, blockedUsers.size()*360));

        int height = 0;
        for (UserInfoServerEvent follower : blockedUsers){
            GraphicalProfile graphicalProfile = new GraphicalProfile(this, follower);
            graphicalProfile.setBounds(0, height, 600, 360);
            mainPanel.add(graphicalProfile);
            height += 360;
        }
        mainPanel.repaint();

        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
        scrollBar.setUnitIncrement(36);
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollPane.validate();
        scrollPane.setBounds(0, 0, 620, 720);
        this.add(scrollPane);

        manageUpdates();
        this.repaint();
    }

    @Override
    public void back() {
        updaterThread.interrupt();
        super.back();
    }

    @Override
    public void manageUpdates() {
        updaterThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                sendClientEvent(new RequestFollowingsEvent(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        updaterThread.start();
    }
}
