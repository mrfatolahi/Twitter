package view.loggeduser.mainmenupage.messagingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserChatsInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserChatsWithBotsInfoEvent;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ChatsPageButton extends MessagingPageButton{
    public ChatsPageButton(Config buttonConfig, MessagingPage messagingPage) {
        super(buttonConfig, messagingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String [] options = {"People", "Bots"};
        int optionNumber = JOptionPane.showOptionDialog(
                null,
                "Open my chats with...",
                "Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                -1
        );
        if (optionNumber == 0){
            try {
                messagingPage.sendClientEvent(new RequestUserChatsInfoEvent(), true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            try {
                messagingPage.sendClientEvent(new RequestUserChatsWithBotsInfoEvent(), true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }
}
