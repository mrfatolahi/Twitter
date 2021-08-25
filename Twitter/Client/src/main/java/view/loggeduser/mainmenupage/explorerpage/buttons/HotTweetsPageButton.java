package view.loggeduser.mainmenupage.explorerpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage.RequestHotTweetsEvent;
import view.loggeduser.mainmenupage.explorerpage.ExplorerPage;

import java.awt.event.MouseEvent;

public class HotTweetsPageButton extends ExpelorerPageButton{
    public HotTweetsPageButton(Config buttonConfig, ExplorerPage explorerPage) {
        super(buttonConfig, explorerPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            explorerPage.sendClientEvent(new RequestHotTweetsEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
