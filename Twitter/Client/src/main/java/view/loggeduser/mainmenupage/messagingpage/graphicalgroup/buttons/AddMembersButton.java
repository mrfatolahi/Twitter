package view.loggeduser.mainmenupage.messagingpage.graphicalgroup.buttons;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.AddMemberToGroupEvent;
import view.loggeduser.mainmenupage.messagingpage.graphicalgroup.models.GraphicalGroup;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AddMembersButton extends JButton implements MouseListener {
    private final GraphicalGroup graphicalGroup;

    public AddMembersButton(GraphicalGroup graphicalGroup) {
        this.graphicalGroup = graphicalGroup;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String newMembersUsernamesString = JOptionPane.showInputDialog(null, "Enter usernames with one space between each two of them, they most be from your followings.");

        String newMemberUsername = "";
        ArrayList<String> newMembersUsernames = new ArrayList<>();
        for (int i = 0; i < newMembersUsernamesString.length(); i++) {
            if (newMembersUsernamesString.charAt(i) == ' '){
                newMembersUsernames.add(newMemberUsername);
                newMemberUsername = "";
                continue;
            }
            newMemberUsername += newMembersUsernamesString.charAt(i);
        }
        newMembersUsernames.add(newMemberUsername);

        try {
            graphicalGroup.sendClientEvent(new AddMemberToGroupEvent(newMembersUsernames, graphicalGroup.getGroup()), false);
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
