package view.loggeduser.mainmenupage.explorerpage;

import config.Config;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.luser.explorerpage.SendHotTweetsServerEvent;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicalprofile.GraphicalProfile;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.loggeduser.mainmenupage.explorerpage.buttons.HotTweetsPageButton;
import view.loggeduser.mainmenupage.explorerpage.buttons.SearchPageButton;

import javax.swing.*;
import java.awt.*;

public class ExplorerPage extends LUserPanel implements ServerAnswerInterpreter {
    private final SearchPageButton searchPageButton;
    private final HotTweetsPageButton hotTweetsPageButton;
    private HotTweetsPage hotTweetsPage;

    public ExplorerPage(MainMenuPage mainMenuPage) {
        super(mainMenuPage, mainMenuPage.getMainPanel(), mainMenuPage.getOwnerId());
        this.setLayout(null);

        Config config = Config.getConfig("Frame");
        this.setBackground(new Color(
                Integer.parseInt(config.getProperty("colorR")),
                Integer.parseInt(config.getProperty("colorG")),
                Integer.parseInt(config.getProperty("colorB"))
        ));

        this.setBounds(
                Integer.parseInt(config.getProperty("x")),
                Integer.parseInt(config.getProperty("y")),
                Integer.parseInt(config.getProperty("width")),
                Integer.parseInt(config.getProperty("height"))
        );

        searchPageButton = new SearchPageButton(Config.getConfig("MenuButtonsPro"), this);
        searchPageButton.setText("Search");
        searchPageButton.setBounds(250, 120, 300, 50);
        this.add(searchPageButton);

        hotTweetsPageButton = new HotTweetsPageButton(Config.getConfig("MenuButtonsPro"), this);
        hotTweetsPageButton.setText("Hot Tweets");
        hotTweetsPageButton.setBounds(250, 190, 300, 50);
        this.add(hotTweetsPageButton);
    }

    public HotTweetsPage getHotTweetsPage() {
        return hotTweetsPage;
    }

    public void setHotTweetsPage(HotTweetsPage hotTweetsPage) {
        this.hotTweetsPage = hotTweetsPage;
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        this.interpretServerAnswer(logicalEvent);
    }

    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        if (serverEvent.getClass() == SendHotTweetsServerEvent.class){
            if (((SendHotTweetsServerEvent) serverEvent).getHotTweets().size() == 0){
                JOptionPane.showMessageDialog(null, "Nothing to show!");
                return;
            }
            this.setHotTweetsPage(new HotTweetsPage(this, ((SendHotTweetsServerEvent) serverEvent).getHotTweets()));
            this.hotTweetsPage.mShow();
        } else
        if (serverEvent.getClass() == UserInfoServerEvent.class){
            if (((UserInfoServerEvent) serverEvent).getUser() == null){
                JOptionPane.showMessageDialog(this, "Not Found");
            } else {
                (new GraphicalProfile(this, ((UserInfoServerEvent) serverEvent))).mShow();
            }
        }
    }
}
