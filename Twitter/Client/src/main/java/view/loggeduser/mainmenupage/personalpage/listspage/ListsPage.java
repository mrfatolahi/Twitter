package view.loggeduser.mainmenupage.personalpage.listspage;

import config.Config;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.luser.personalpage.listspage.BlockedUsersInfoEvent;
import events.serverevents.luser.personalpage.listspage.FollowersInfoEvent;
import events.serverevents.luser.personalpage.listspage.FollowingsInfoEvent;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;
import view.loggeduser.mainmenupage.personalpage.listspage.buttons.BlockedUsersButton;
import view.loggeduser.mainmenupage.personalpage.listspage.buttons.FollowersButton;
import view.loggeduser.mainmenupage.personalpage.listspage.buttons.FollowingsButton;

import javax.swing.*;
import java.awt.*;

public class ListsPage extends LUserPanel implements ServerAnswerInterpreter {
    private final JButton followersPageButton;
    private final JButton followingsPageButton;
    private final JButton blockedUsersPageButton;

    public ListsPage(PersonalPage personalPage) {
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

        followersPageButton = new FollowersButton(Config.getConfig("MenuButtonsPro"), this);
        followersPageButton.setText("Followers");
        followersPageButton.setBounds(250, 120, 300, 50);
        this.add(followersPageButton);

        followingsPageButton = new FollowingsButton(Config.getConfig("MenuButtonsPro"), this);
        followingsPageButton.setText("Followings");
        followingsPageButton.setBounds(250, 190, 300, 50);
        this.add(followingsPageButton);

        blockedUsersPageButton = new BlockedUsersButton(Config.getConfig("MenuButtonsPro"), this);
        blockedUsersPageButton.setText("Blocked Users");
        blockedUsersPageButton.setBounds(250, 260, 300, 50);
        this.add(blockedUsersPageButton);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        interpretServerAnswer(logicalEvent);
    }

    @Override
    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        if ((serverEvent.getClass() == FollowingsInfoEvent.class)) {
            (new FollowingsPage(this, ((FollowingsInfoEvent) serverEvent).getFollowings())).mShow();
        } else
        if ((serverEvent.getClass() == FollowersInfoEvent.class)) {
            (new FollowersPage(this, ((FollowersInfoEvent) serverEvent).getFollowers())).mShow();
        } else
        if ((serverEvent.getClass() == BlockedUsersInfoEvent.class)) {
            (new BlockedUsersPage(this, ((BlockedUsersInfoEvent) serverEvent).getBlockedUsers())).mShow();
        }
    }
}
