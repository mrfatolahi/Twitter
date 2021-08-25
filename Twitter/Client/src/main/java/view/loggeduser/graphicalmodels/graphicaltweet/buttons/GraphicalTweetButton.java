package view.loggeduser.graphicalmodels.graphicaltweet.buttons;

import config.Config;
import view.mainclasses.MButton;
import view.loggeduser.graphicalmodels.graphicaltweet.model.GraphicalTweet;

import java.awt.*;

public class GraphicalTweetButton extends MButton {
    private final GraphicalTweet graphicalTweet;

    public GraphicalTweetButton(Config buttonConfig, GraphicalTweet graphicalTweet) {
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
        this.graphicalTweet = graphicalTweet;
    }

    public GraphicalTweet getGraphicalTweet() {
        return graphicalTweet;
    }
}
