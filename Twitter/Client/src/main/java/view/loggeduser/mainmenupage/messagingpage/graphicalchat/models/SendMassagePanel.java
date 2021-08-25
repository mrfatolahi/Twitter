package view.loggeduser.mainmenupage.messagingpage.graphicalchat.models;

import events.EventManager;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.NewChatMassageEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import models.other.hyperlink.HyperLinkType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SendMassagePanel extends JPanel implements EventManager {
    private final GraphicalChat graphicalChat;
    private final JTextArea textArea;
    private final JButton sendButton;


    public SendMassagePanel(GraphicalChat graphicalChat, int receiverId) {
        this.graphicalChat = graphicalChat;
        this.setBounds(0, 600, 600, 100);
        this.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(10, 0, 390, 70);
        this.add(textArea);

        sendButton = new JButton("Send");

        sendButton.setBounds(10, 70, 390, 30);
        sendButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
                    fileChooser.setFileFilter(filter);
                    String filePath = "";
                    if (fileChooser.showOpenDialog(graphicalChat) == JFileChooser.APPROVE_OPTION){
                        filePath = fileChooser.getSelectedFile().getPath();
                    }

                    HyperLinkType hyperLinkType = null;
                    String hyperLinkDestination = null;
                    String hyperLinkText = null;

                    int a = JOptionPane.showConfirmDialog(null, "Do you want to add a hyperlink?", "",  JOptionPane.YES_NO_OPTION);

                    System.out.println("a = " + a);

                    if (a == 0){
                        String[] options = {"GroupLink", "ChatLink", "GroupInviteLink", "BotChatLink"};
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
                        System.out.println("optionNumber = " + optionNumber);

                        hyperLinkDestination = JOptionPane.showInputDialog(null, "Enter username/group name:");
                        hyperLinkText = JOptionPane.showInputDialog(null, "Enter hyperlink text:");
                        switch (optionNumber){
                            case 0:
                                hyperLinkType = HyperLinkType.GROUP_LINK;
                                break;
                            case 1:
                                hyperLinkType = HyperLinkType.PERSON_CHAT_LINK;
                                break;
                            case 2:
                                hyperLinkType = HyperLinkType.GROUP_INVITE_LINK;
                                break;
                            case 3:
                                hyperLinkType = HyperLinkType.BOT_CHAT_LINK;
                                break;
                        }
                    }

                    sendClientEvent(new NewChatMassageEvent(receiverId, textArea.getText(), filePath, hyperLinkText, hyperLinkType, hyperLinkDestination), false);
                    textArea.setText("");
                    textArea.repaint();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        });
        this.add(sendButton);

        this.repaint();

    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        graphicalChat.sendNewChatMassageEvent(((NewChatMassageEvent) clientEvent), hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {

    }
}
