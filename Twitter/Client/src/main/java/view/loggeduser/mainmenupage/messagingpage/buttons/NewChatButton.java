package view.loggeduser.mainmenupage.messagingpage.buttons;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestMakingNewChatEvent;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class NewChatButton extends MessagingPageButton{
    public NewChatButton(Config buttonConfig, MessagingPage messagingPage) {
        super(buttonConfig, messagingPage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String username = JOptionPane.showInputDialog("Enter Username:");
        try {
            messagingPage.sendClientEvent(new RequestMakingNewChatEvent(username), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
//        GraphicalChat graphicalChat = new GraphicalChat(messagingPage, null);
//        graphicalChat.setPreferredSize(new Dimension(400,6000));
//        JPanel panel = new JPanel();
//        JScrollPane jScrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
////        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
//
////        scrollBar.setBounds(0,0,30, 600);
//        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
////        scrollBar.set
//        jScrollPane.setVerticalScrollBar(scrollBar);
////        jScrollPane.setWheelScrollingEnabled(true);
//
////        jScrollPane.setViewportView(graphicalChat);
//        jScrollPane.setBounds(0, 0, 500, 600);
//        jScrollPane.validate();
//        messagingPage.getMainPanel().removeAll();
//        messagingPage.getMainPanel().add(jScrollPane);
//        messagingPage.getMainPanel().repaint();
//        System.out.println("SSSSADASDS");
    }
}
