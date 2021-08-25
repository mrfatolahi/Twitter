package view.loggeduser.mainmenupage.settingpage;

import config.Config;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.loggeduser.mainmenupage.settingpage.buttons.DeleteAccountButton;
import view.loggeduser.mainmenupage.settingpage.buttons.LogOutButton;
import view.loggeduser.mainmenupage.settingpage.buttons.PrivacyButton;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.PrivacySettingPage;

import java.awt.*;

public class SettingPage extends LUserPanel {
    private final MainMenuPage mainMenuPage;
    private final PrivacyButton privacyButton;
    private final DeleteAccountButton deleteAccountButton;
    private final LogOutButton logOutButton;
    private PrivacySettingPage privacySettingPage;

    public SettingPage(MainMenuPage mainMenuPage) {
        super(mainMenuPage, mainMenuPage.getMainPanel(), mainMenuPage.getOwnerId());
        this.mainMenuPage = mainMenuPage;
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

        privacyButton = new PrivacyButton(Config.getConfig("MenuButtonsPro"), this);
        privacyButton.setText("Privacy");
        privacyButton.setBounds(250, 120, 300, 50);
        this.add(privacyButton);

        deleteAccountButton = new DeleteAccountButton(Config.getConfig("MenuButtonsPro"), this);
        deleteAccountButton.setText("Delete Account");
        deleteAccountButton.setBounds(250, 190, 300, 50);
        this.add(deleteAccountButton);

        logOutButton = new LogOutButton(Config.getConfig("MenuButtonsPro"), this);
        logOutButton.setText("Log Out");
        logOutButton.setBounds(250, 260, 300, 50);
        this.add(logOutButton);
    }

    public PrivacySettingPage getPrivacySettingPage() {
        return privacySettingPage;
    }

    public void setPrivacySettingPage(PrivacySettingPage privacySettingPage) {
        this.privacySettingPage = privacySettingPage;
    }

    public MainMenuPage getMainMenuPage() {
        return mainMenuPage;
    }
}
