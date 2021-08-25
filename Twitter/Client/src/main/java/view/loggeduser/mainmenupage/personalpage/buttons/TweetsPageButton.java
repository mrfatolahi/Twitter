package view.loggeduser.mainmenupage.personalpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.RequestUserTweetsEvent;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;

import java.awt.event.MouseEvent;

public class TweetsPageButton extends PersonalPageButton{
    public TweetsPageButton(Config buttonConfig, PersonalPage personalPage) {
        super(buttonConfig, personalPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {

            personalPage.sendClientEvent(new RequestUserTweetsEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
