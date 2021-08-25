package view.loggeduser.graphicalmodels.graphicaltweet.listeners;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.RetweetEvent;
import view.loggeduser.graphicalmodels.graphicaltweet.buttons.GraphicalTweetButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RetweetButtonListener implements MouseListener {
    private final GraphicalTweetButton graphicalTweetButton;

    public RetweetButtonListener(GraphicalTweetButton graphicalTweetButton) {
        this.graphicalTweetButton = graphicalTweetButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String text = JOptionPane.showInputDialog("Enter Retweet Text:");
        String tags = JOptionPane.showInputDialog("Enter Retweet Tags:");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
        fileChooser.setFileFilter(filter);
        String filePath = "";
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getPath();
        }
        try {
            graphicalTweetButton.getGraphicalTweet().sendClientEvent(new RetweetEvent(graphicalTweetButton.getGraphicalTweet().getTweet(), text, tags, filePath), false);
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
