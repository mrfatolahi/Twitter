package view.loggeduser.mainmenupage.explorerpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage.ERequestUserInfoEvent;
import view.loggeduser.mainmenupage.explorerpage.ExplorerPage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class SearchPageButton extends ExpelorerPageButton{
    public SearchPageButton(Config buttonConfig, ExplorerPage explorerPage) {
        super(buttonConfig, explorerPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String username = JOptionPane.showInputDialog("Enter Username:");
        try {
            explorerPage.sendClientEvent(new ERequestUserInfoEvent(username), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
