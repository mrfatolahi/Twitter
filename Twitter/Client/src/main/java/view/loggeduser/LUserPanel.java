package view.loggeduser;

import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.clientevents.main.DeclareClientExitCE;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.main.ServerEvent;
import view.mainclasses.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class LUserPanel extends JPanel implements EventManager, ServerAnswerInterpreter {
    protected final MainPanel mainPanel;
    protected final LUserPanel previousPage;
    protected final int ownerId;

    public LUserPanel(LUserPanel previousPage, MainPanel mainPanel, int ownerId) {
        this.previousPage = previousPage;
        this.mainPanel = mainPanel;
        this.ownerId = ownerId;

        JButton backButton = new JButton("Back");
        backButton.setBounds(720, 640, 70, 30);
        backButton.setBackground(Color.WHITE);
        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                back();
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.CYAN);
            }
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(Color.WHITE);
            }
        });
        this.add(backButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(720, 670, 70, 30);
        exitButton.setBackground(Color.WHITE);
        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exit();
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(Color.WHITE);
            }
        });
        this.add(exitButton);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public LUserPanel getPreviousPage() {
        return previousPage;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void mShow(){
        mainPanel.removeAll();
        mainPanel.add(this);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void back(){
        mainPanel.removeAll();
        mainPanel.add(previousPage);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void exit(){
        try {
            sendClientEvent(new DeclareClientExitCE(), false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.exit(0);
    }

    public void reShow(){
        back();
        mShow();
    }


    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        previousPage.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {

    }

    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {

    }
}
