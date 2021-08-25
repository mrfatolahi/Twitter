package view.loggeduser.mainmenupage.messagingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.CreateNewGroupEvent;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NewGroupButton extends MessagingPageButton{
    public NewGroupButton(Config buttonConfig, MessagingPage messagingPage) {
        super(buttonConfig, messagingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String groupName = JOptionPane.showInputDialog(null, "Enter group name:");
        String membersUsernamesString = JOptionPane.showInputDialog(null, "Enter primary members usernames with one space between each two of them, they most be from your followings.");

        String memberUsername = "";
        ArrayList<String> memberUsernamesList = new ArrayList<>();
        for (int i = 0; i < membersUsernamesString.length(); i++) {
            if (membersUsernamesString.charAt(i) == ' '){
                memberUsernamesList.add(memberUsername);
                memberUsername = "";
                continue;
            }
            memberUsername += membersUsernamesString.charAt(i);
        }

        memberUsernamesList.add(memberUsername);

        try {
            messagingPage.sendClientEvent(new CreateNewGroupEvent(groupName, memberUsernamesList), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Done!");
    }
}
