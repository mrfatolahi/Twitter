package view.loggeduser.mainmenupage.messagingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserSavedMessagesEvent;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;
import java.awt.event.MouseEvent;

public class SavedMessagesPageButton extends MessagingPageButton{
    public SavedMessagesPageButton(Config buttonConfig, MessagingPage messagingPage) {
        super(buttonConfig, messagingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            messagingPage.sendClientEvent(new RequestUserSavedMessagesEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
