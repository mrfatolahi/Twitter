package view.loggeduser.mainmenupage.settingpage.privacysettingpage;

import config.Config;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.settingpage.SettingPage;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons.AccountActivenessSettingButton;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons.AccountPublicitySettingButton;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons.ChangePasswordButton;
import view.loggeduser.mainmenupage.settingpage.privacysettingpage.buttons.LastseenSettingButton;

import java.awt.*;

public class PrivacySettingPage extends LUserPanel {
    private final AccountPublicitySettingButton accountPublicitySettingButton;
    private final LastseenSettingButton lastseenSettingButton;
    private final AccountActivenessSettingButton accountActivenessSettingButton;
    private final ChangePasswordButton changePasswordButton;

    public PrivacySettingPage(SettingPage settingPage) {
        super(settingPage, settingPage.getMainPanel(), settingPage.getOwnerId());
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

        accountPublicitySettingButton = new AccountPublicitySettingButton(Config.getConfig("MenuButtonsPro"), this);
        accountPublicitySettingButton.setText("Account Publicity");
        accountPublicitySettingButton.setBounds(250, 120, 300, 50);
        this.add(accountPublicitySettingButton);

        lastseenSettingButton = new LastseenSettingButton(Config.getConfig("MenuButtonsPro"), this);
        lastseenSettingButton.setText("Lastseen Setting");
        lastseenSettingButton.setBounds(250, 190, 300, 50);
        this.add(lastseenSettingButton);

        accountActivenessSettingButton = new AccountActivenessSettingButton(Config.getConfig("MenuButtonsPro"), this);
        accountActivenessSettingButton.setText("Account Activeness");
        accountActivenessSettingButton.setBounds(250, 260, 300, 50);
        this.add(accountActivenessSettingButton);

        changePasswordButton = new ChangePasswordButton(Config.getConfig("MenuButtonsPro"), this);
        changePasswordButton.setText("Change Password");
        changePasswordButton.setBounds(250, 330, 300, 50);
        this.add(changePasswordButton);
    }
}
