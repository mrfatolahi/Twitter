package view.loggeduser.mainmenupage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.timelinepage.RequestTimeLinePageTweets;
import view.loggeduser.mainmenupage.MainMenuPage;

import java.awt.event.MouseEvent;

public class TimeLineButton extends MenuButton {
    public TimeLineButton(Config buttonConfig, MainMenuPage mainMenuPage) {
        super(buttonConfig, mainMenuPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            mainMenuPage.sendClientEvent(new RequestTimeLinePageTweets(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

