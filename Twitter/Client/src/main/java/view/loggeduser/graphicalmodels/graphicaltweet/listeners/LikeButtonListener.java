package view.loggeduser.graphicalmodels.graphicaltweet.listeners;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.NewLikeEvent;
import view.loggeduser.graphicalmodels.graphicaltweet.buttons.GraphicalTweetButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LikeButtonListener implements MouseListener {
    private final GraphicalTweetButton graphicalTweetButton;

    public LikeButtonListener(GraphicalTweetButton graphicalTweetButton) {
        this.graphicalTweetButton = graphicalTweetButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            graphicalTweetButton.getGraphicalTweet().sendClientEvent(new NewLikeEvent(graphicalTweetButton.getGraphicalTweet().getTweet()), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
