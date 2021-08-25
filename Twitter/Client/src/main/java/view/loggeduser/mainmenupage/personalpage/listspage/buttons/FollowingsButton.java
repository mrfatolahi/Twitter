package view.loggeduser.mainmenupage.personalpage.listspage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestFollowingsEvent;
import view.loggeduser.mainmenupage.personalpage.listspage.ListsPage;

import java.awt.event.MouseEvent;

public class FollowingsButton extends ListsPageButton{
    public FollowingsButton(Config buttonConfig, ListsPage listsPage) {
        super(buttonConfig, listsPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            listsPage.sendClientEvent(new RequestFollowingsEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
