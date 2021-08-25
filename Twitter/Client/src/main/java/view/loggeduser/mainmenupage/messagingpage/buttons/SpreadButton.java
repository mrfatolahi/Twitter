package view.loggeduser.mainmenupage.messagingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.SpreadEvent;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SpreadButton extends MessagingPageButton{
    public SpreadButton(Config buttonConfig, MessagingPage messagingPage) {
        super(buttonConfig, messagingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String text = JOptionPane.showInputDialog(null, "Enter text.");
        String receiversUsernamesString = JOptionPane.showInputDialog(null, "Enter usernames with one space between each two of them, you must have started a chat with them before.");

        String receiverUsername = "";
        ArrayList<String> memberUsernamesList = new ArrayList<>();
        for (int i = 0; i < receiversUsernamesString.length(); i++) {
            if (receiversUsernamesString.charAt(i) == ' '){
                memberUsernamesList.add(receiverUsername);
                receiverUsername = "";
                continue;
            }
            receiverUsername += receiversUsernamesString.charAt(i);
        }
        memberUsernamesList.add(receiverUsername);

        try {
            messagingPage.sendClientEvent(new SpreadEvent(text, memberUsernamesList), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Done!");
    }
}
