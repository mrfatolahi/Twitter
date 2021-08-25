package view.loggeduser.mainmenupage.messagingpage.graphicalgroup.buttons;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.AddMemberToGroupEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.LeaveGroupEvent;
import view.loggeduser.mainmenupage.messagingpage.graphicalgroup.models.GraphicalGroup;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LeaveGroupButton extends JButton implements MouseListener {
    private final GraphicalGroup graphicalGroup;

    public LeaveGroupButton(GraphicalGroup graphicalGroup) {
        this.graphicalGroup = graphicalGroup;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            graphicalGroup.sendClientEvent(new LeaveGroupEvent(graphicalGroup.getGroup()), false);
            graphicalGroup.back();
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
