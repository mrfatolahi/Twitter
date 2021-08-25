package view.loggeduser.mainmenupage.personalpage.listspage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestFollowersEvent;
import view.loggeduser.mainmenupage.personalpage.listspage.ListsPage;

import java.awt.event.MouseEvent;

public class FollowersButton extends ListsPageButton{
    public FollowersButton(Config buttonConfig, ListsPage listsPage) {
        super(buttonConfig, listsPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            listsPage.sendClientEvent(new RequestFollowersEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
