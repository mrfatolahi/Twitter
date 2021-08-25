package view.loggeduser.mainmenupage.messagingpage.graphicalchat.buttons;

import config.Config;
import view.mainclasses.MButton;
import view.loggeduser.mainmenupage.messagingpage.graphicalchat.models.ChatPanel;

import java.awt.*;

public class GraphicalChatButton extends MButton {
    private final ChatPanel chatPanel;

    public GraphicalChatButton(Config buttonConfig, ChatPanel chatPanel) {
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
        this.chatPanel = chatPanel;
    }
}
