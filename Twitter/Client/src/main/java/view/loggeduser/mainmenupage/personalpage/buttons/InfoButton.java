package view.loggeduser.mainmenupage.personalpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.RequestUserInfoEvent;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;

import java.awt.event.MouseEvent;

public class InfoButton extends PersonalPageButton{
    public InfoButton(Config buttonConfig, PersonalPage personalPage) {
        super(buttonConfig, personalPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            personalPage.sendClientEvent(new RequestUserInfoEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
