package view.loggeduser.mainmenupage.personalpage.buttons;

import config.Config;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;
import view.loggeduser.mainmenupage.personalpage.listspage.ListsPage;

import java.awt.event.MouseEvent;

public class ListsPageButton extends PersonalPageButton{
    public ListsPageButton(Config buttonConfig, PersonalPage personalPage) {
        super(buttonConfig, personalPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        personalPage.setListsPage(new ListsPage(personalPage));
        personalPage.getListsPage().mShow();
    }
}
