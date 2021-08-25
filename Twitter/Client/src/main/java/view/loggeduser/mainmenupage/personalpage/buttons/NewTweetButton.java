package view.loggeduser.mainmenupage.personalpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.NewTweetEvent;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseEvent;

public class NewTweetButton extends PersonalPageButton{
    public NewTweetButton(Config buttonConfig, PersonalPage personalPage) {
        super(buttonConfig, personalPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String text = JOptionPane.showInputDialog("Enter Tweet Text:");
        String tags = JOptionPane.showInputDialog("Enter Tweet Tags:");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
        fileChooser.setFileFilter(filter);
        String filePath = "";
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getPath();
        }
        try {
            personalPage.sendClientEvent(new NewTweetEvent(text, tags, filePath), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
