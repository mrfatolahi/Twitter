package view.loggeduser.mainmenupage.messagingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserGroupsEvent;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;

import java.awt.event.MouseEvent;

public class GroupsButton extends MessagingPageButton{
    public GroupsButton(Config buttonConfig, MessagingPage messagingPage) {
        super(buttonConfig, messagingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            messagingPage.sendClientEvent(new RequestUserGroupsEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
