package view.loggeduser.graphicalmodels.graphicaltweet.listeners;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.TweetForwardEvent;
import view.loggeduser.graphicalmodels.graphicaltweet.buttons.GraphicalTweetButton;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ForwardButtonListener implements MouseListener {
    private final GraphicalTweetButton graphicalTweetButton;

    public ForwardButtonListener(GraphicalTweetButton graphicalTweetButton) {
        this.graphicalTweetButton = graphicalTweetButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String receiverUsername = JOptionPane.showInputDialog("Enter Receiver Username:");
        try {
            graphicalTweetButton.getGraphicalTweet().sendClientEvent(new TweetForwardEvent(graphicalTweetButton.getGraphicalTweet().getTweet(), receiverUsername), false);
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
