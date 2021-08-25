package view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfileImageEvent;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseEvent;

public class EditProfileImageButton extends EditProfilePageButton {

    public EditProfileImageButton(Config buttonConfig, EditProfilePage editProfilePage) {
        super(buttonConfig, editProfilePage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
        fileChooser.setFileFilter(filter);
        String filePath = "";
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getPath();
        }
        try {
            editProfilePage.sendClientEvent(new EditProfileImageEvent(filePath), false);
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
