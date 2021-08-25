package view.loggeduser.mainmenupage.personalpage.listspage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestBlockedUsersEvent;
import view.loggeduser.mainmenupage.personalpage.listspage.ListsPage;

import java.awt.event.MouseEvent;

public class BlockedUsersButton extends ListsPageButton{
    public BlockedUsersButton(Config buttonConfig, ListsPage listsPage) {
        super(buttonConfig, listsPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            listsPage.sendClientEvent(new RequestBlockedUsersEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
