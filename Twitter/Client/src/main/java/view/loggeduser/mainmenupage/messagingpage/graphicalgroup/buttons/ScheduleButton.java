package view.loggeduser.mainmenupage.messagingpage.graphicalgroup.buttons;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.AddMemberToGroupEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.NewScheduledGroupMessageEvent;
import view.loggeduser.mainmenupage.messagingpage.graphicalgroup.models.GraphicalGroup;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ScheduleButton extends JButton implements MouseListener {
    private final GraphicalGroup graphicalGroup;

    public ScheduleButton(GraphicalGroup graphicalGroup) {
        this.graphicalGroup = graphicalGroup;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String text = JOptionPane.showInputDialog(null, "Enter text:");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
        fileChooser.setFileFilter(filter);
        String filePath = "";
        if (fileChooser.showOpenDialog(graphicalGroup) == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getPath();
        }

        int hour = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter hour:"));
        int minute = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter minute:"));

        try {
            graphicalGroup.sendClientEvent(new NewScheduledGroupMessageEvent(graphicalGroup.getGroup().getUsersIds(),text, filePath, true, hour, minute), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Done!");

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
