package view.loggeduser.mainmenupage.messagingpage.graphicalgroup.models;

import events.EventManager;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.NewGroupMessageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.RequestNewQuizEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import models.other.hyperlink.HyperLinkType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class GSendMessagePanel extends JPanel implements EventManager {
    private final GraphicalGroup graphicalGroup;
    private final JTextArea textArea;
    private final JButton sendButton;



    public GSendMessagePanel(GraphicalGroup graphicalGroup, ArrayList<Integer> receiversId) {
        this.graphicalGroup = graphicalGroup;
        this.setBounds(0, 600, 600, 70);
        this.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(0, 0, 400, 40);
        this.add(textArea);

        sendButton = new JButton("Send");

        sendButton.setBounds(0, 40, 400, 30);
        sendButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
                    fileChooser.setFileFilter(filter);
                    String filePath = "";
                    if (fileChooser.showOpenDialog(graphicalGroup) == JFileChooser.APPROVE_OPTION){
                        filePath = fileChooser.getSelectedFile().getPath();
                    }

                    HyperLinkType hyperLinkType = null;
                    String hyperLinkDestination = null;
                    String hyperLinkText = null;

                    int a = JOptionPane.showConfirmDialog(null, "Do you want to add a hyperlink?", "",  JOptionPane.YES_NO_OPTION);

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
                    if (textArea.getText().equals("@QuizBot")){
                        graphicalGroup.sendClientEvent(new RequestNewQuizEvent(receiversId), false);
                    }else {
                        sendClientEvent(new NewGroupMessageEvent(receiversId, textArea.getText(), filePath, hyperLinkText, hyperLinkType, hyperLinkDestination), false);
                    }

                    textArea.setText("");
                    textArea.repaint();
                    graphicalGroup.initializeChatPanel();
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
        graphicalGroup.sendNewGroupMassageEvent(((NewGroupMessageEvent) clientEvent), hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {

    }
}
