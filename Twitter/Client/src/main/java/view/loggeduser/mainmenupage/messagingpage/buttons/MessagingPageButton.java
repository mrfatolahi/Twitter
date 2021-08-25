package view.loggeduser.mainmenupage.messagingpage.buttons;

import config.Config;
import view.mainclasses.MButton;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;

import java.awt.*;

public abstract class MessagingPageButton extends MButton {
    protected final MessagingPage messagingPage;


    public MessagingPageButton(Config buttonConfig, MessagingPage messagingPage) {
        super(
                new Color(
                        buttonConfig.getPropertyAsInt("foregroundColor1R"),
                        buttonConfig.getPropertyAsInt("foregroundColor1G"),
                        buttonConfig.getPropertyAsInt("foregroundColor1B")
                ),
                new Color(
                        buttonConfig.getPropertyAsInt("backgroundColor1R"),
                        buttonConfig.getPropertyAsInt("backgroundColor1G"),
                        buttonConfig.getPropertyAsInt("backgroundColor1B")
                ),
                new Color(
                        buttonConfig.getPropertyAsInt("foregroundColor2R"),
                        buttonConfig.getPropertyAsInt("foregroundColor2G"),
                        buttonConfig.getPropertyAsInt("foregroundColor2B")
                ),
                new Color(
                        buttonConfig.getPropertyAsInt("backgroundColor2R"),
                        buttonConfig.getPropertyAsInt("backgroundColor2G"),
                        buttonConfig.getPropertyAsInt("backgroundColor2B")
                ),
                new Font(
                        buttonConfig.getProperty("fontName"),
                        Font.PLAIN,
                        buttonConfig.getPropertyAsInt("fontSize")
                )
        );

        int buttonsWidth = Config.getConfig("MenuButtonsPro").getPropertyAsInt("width");
        int buttonsHeight = Config.getConfig("MenuButtonsPro").getPropertyAsInt("height");

        this.setMaximumSize(new Dimension(buttonsWidth, buttonsHeight));

        this.messagingPage = messagingPage;
    }
}
