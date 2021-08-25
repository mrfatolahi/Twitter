package view.loggeduser.mainmenupage;

import config.Config;
import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.luser.explorerpage.ExplorerPageServerEvent;
import events.serverevents.luser.messagingpage.MessagingPageServerEvent;
import events.serverevents.luser.personalpage.PersonalPageServerEvent;
import events.serverevents.luser.timelinepage.SendTimeLineTweetsEvent;
import events.serverevents.luser.timelinepage.TimeLinePageServerEvent;
import events.serverevents.main.ServerEvent;
import events.serverevents.luser.*;
import network.NetworkManager;
import view.mainclasses.MainPanel;
import view.loggeduser.LUserGraphicalAgent;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.buttons.*;
import view.loggeduser.mainmenupage.explorerpage.ExplorerPage;
import view.loggeduser.mainmenupage.messagingpage.MessagingPage;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;
import view.loggeduser.mainmenupage.settingpage.SettingPage;
import view.loggeduser.mainmenupage.timelinepage.TimeLinePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenuPage extends LUserPanel implements EventManager, ServerAnswerInterpreter {
    private final LUserGraphicalAgent lUserGraphicalAgent;
    private PersonalPage personalPage;
    private TimeLinePage timeLinePage;
    private ExplorerPage explorerPage;
    private MessagingPage messagingPage;
    private SettingPage settingPage;
    private final PersonalPageButton personalPageButton;
    private final TimeLineButton timeLineButton;
    private final ExplorerButton explorerButton;
    private final MessagingButton messagingButton;
    private final SettingButton settingButton;
    private final JButton tryToReconnectButton;

    public MainMenuPage(LUserGraphicalAgent lUserGraphicalAgent, MainPanel mainPanel, int ownerId) {
        super(null, mainPanel, ownerId);
        this.lUserGraphicalAgent = lUserGraphicalAgent;
        this.setLayout(null);
        Config config = Config.getConfig("Frame");
        this.setBackground(new Color(
                Integer.parseInt(config.getProperty("colorR")),
                Integer.parseInt(config.getProperty("colorG")),
                Integer.parseInt(config.getProperty("colorB"))
        ));

        this.setBounds(
                Integer.parseInt(config.getProperty("x")),
                Integer.parseInt(config.getProperty("y")),
                Integer.parseInt(config.getProperty("width")),
                Integer.parseInt(config.getProperty("height"))
        );


        personalPageButton = new PersonalPageButton(Config.getConfig("MenuButtonsPro"), this);
        personalPageButton.setText("Personal Page");
        personalPageButton.setBounds(250, 120, 300, 50);
        this.add(personalPageButton);

        timeLineButton = new TimeLineButton(Config.getConfig("MenuButtonsPro"), this);
        timeLineButton.setText("Time Line");
        timeLineButton.setBounds(250, 190, 300, 50);
        this.add(timeLineButton);

        explorerButton = new ExplorerButton(Config.getConfig("MenuButtonsPro"), this);
        explorerButton.setText("Explorer");
        explorerButton.setBounds(250, 260, 300, 50);
        this.add(explorerButton);

        messagingButton = new MessagingButton(Config.getConfig("MenuButtonsPro"), this);
        messagingButton.setText("Messaging");
        messagingButton.setBounds(250, 330, 300, 50);
        this.add(messagingButton);

        settingButton = new SettingButton(Config.getConfig("MenuButtonsPro"), this);
        settingButton.setText("Setting");
        settingButton.setBounds(250, 400, 300, 50);
        this.add(settingButton);

        tryToReconnectButton = new JButton("Reconnect");
        tryToReconnectButton.setFont(new Font(null, Font.PLAIN, 11));
        tryToReconnectButton.setBounds(0, 0, 90, 30);
        tryToReconnectButton.setBackground(Color.CYAN);
        tryToReconnectButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (NetworkManager.isConnectedToServer()){
                    JOptionPane.showMessageDialog(null, "You are connected!");
                }else {
                    lUserGraphicalAgent.tryToReconnect();
                }
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        this.add(tryToReconnectButton);

        mainPanel.removeAll();
        mainPanel.add(this);
        mainPanel.repaint();
    }

    public void setPersonalPage(PersonalPage personalPage) {
        this.personalPage = personalPage;
    }

    public void setTimeLinePage(TimeLinePage timeLinePage) {
        this.timeLinePage = timeLinePage;
    }

    public void setExpelorerPage(ExplorerPage explorerPage) {
        this.explorerPage = explorerPage;
    }

    public void setMessagingPage(MessagingPage messagingPage) {
        this.messagingPage = messagingPage;
    }

    public void setSettingPage(SettingPage settingPage) {
        this.settingPage = settingPage;
    }

    public PersonalPage getPersonalPage() {
        return personalPage;
    }

    public TimeLinePage getTimeLinePage() {
        return timeLinePage;
    }

    public ExplorerPage getExpelorerPage() {
        return explorerPage;
    }

    public MessagingPage getMessagingPage() {
        return messagingPage;
    }

    public SettingPage getSettingPage() {
        return settingPage;
    }

    public LUserGraphicalAgent getlUserGraphicalAgent() {
        return lUserGraphicalAgent;
    }

    @Override
    public void back() {
        lUserGraphicalAgent.openAWellComePage();
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        lUserGraphicalAgent.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        this.interpretServerAnswer(logicalEvent);
    }

    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        try {
            explorerPage.receiveServerEvent((ExplorerPageServerEvent) serverEvent);
        } catch (ClassCastException e){
            try {
                messagingPage.receiveServerEvent((MessagingPageServerEvent) serverEvent);
            } catch (ClassCastException t){
                try {
                    personalPage.receiveServerEvent((PersonalPageServerEvent) serverEvent);
                } catch (ClassCastException r){
                    try {
                        interpretTimeLinePageEvent((TimeLinePageServerEvent) serverEvent);
                    } catch (ClassCastException d){
                        System.out.println();
                    }
                }
            }
        }
    }

    private void interpretTimeLinePageEvent(TimeLinePageServerEvent timeLinePageServerEvent){
        if (timeLinePageServerEvent.getClass() == SendTimeLineTweetsEvent.class){
            if (((SendTimeLineTweetsEvent) timeLinePageServerEvent).getTweets().size() == 0){
                JOptionPane.showMessageDialog(null, "Nothing to show!");
                return;
            }
            try {
                this.setTimeLinePage(new TimeLinePage(this, ((SendTimeLineTweetsEvent) timeLinePageServerEvent).getTweets()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.timeLinePage.mShow();
        }
    }
}
