package view.loggeduser.graphicalmodels.graphicaltweet.listeners;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.NewCommentEvent;
import view.loggeduser.graphicalmodels.graphicaltweet.buttons.GraphicalTweetButton;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class CommentButtomListener implements MouseListener {
    private final GraphicalTweetButton graphicalTweetButton;

    public CommentButtomListener(GraphicalTweetButton graphicalTweetButton) {
        this.graphicalTweetButton = graphicalTweetButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String text = JOptionPane.showInputDialog("Enter Comment Text:");
        try {
            graphicalTweetButton.getGraphicalTweet().sendClientEvent(new NewCommentEvent(graphicalTweetButton.getGraphicalTweet().getTweet(), text), false);
        } catch (IOException ioException) {
            ioException.printStackTrace();
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
