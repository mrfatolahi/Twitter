package view.loggeduser.graphicalmodels.graphicaltweet.listeners;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.AddTweetToSavedMessages;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.BlockTweetOwnerEvent;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.MuteTweetOwnerEvent;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.ReportTweetEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicaltweet.buttons.GraphicalTweetButton;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OptionsButtonListener implements MouseListener {
    private final GraphicalTweetButton graphicalTweetButton;

    public OptionsButtonListener(GraphicalTweetButton graphicalTweetButton) throws Exception {
        this.graphicalTweetButton = graphicalTweetButton;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String [] options = {"Block User", "Mute User", "Report", "Add To Savad Massages"};
        int optionNumber = JOptionPane.showOptionDialog(
                null,
                "Choose an option",
                "Options",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                0
        );

        switch (optionNumber){
            case 0:
                try {
                    graphicalTweetButton.getGraphicalTweet().sendClientEvent((new BlockTweetOwnerEvent(graphicalTweetButton.getGraphicalTweet().getTweet())), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case 1:
                try {
                    graphicalTweetButton.getGraphicalTweet().sendClientEvent(new MuteTweetOwnerEvent(graphicalTweetButton.getGraphicalTweet().getTweet()), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case 2:
                try {
                    graphicalTweetButton.getGraphicalTweet().sendClientEvent(new ReportTweetEvent(graphicalTweetButton.getGraphicalTweet().getTweet()), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case 3:
                try {
                    graphicalTweetButton.getGraphicalTweet().sendClientEvent(new AddTweetToSavedMessages(((LUserPanel) graphicalTweetButton.getGraphicalTweet().getGraphicEventManager()).getOwnerId(), graphicalTweetButton.getGraphicalTweet().getTweet()), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
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
