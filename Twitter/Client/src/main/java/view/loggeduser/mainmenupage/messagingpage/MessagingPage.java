package view.loggeduser.mainmenupage.messagingpage;

import config.Config;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserChatsInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserChatsWithBotsInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserGroupsEvent;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.luser.messagingpage.*;
import events.serverevents.luser.messagingpage.graphicalchat.GraphicalChatServerEvent;
import events.serverevents.luser.messagingpage.graphicalgroup.GraphicalGroupServerEvent;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicaltweet.model.GraphicalTweet;
import view.loggeduser.mainmenupage.messagingpage.buttons.*;
import view.loggeduser.mainmenupage.MainMenuPage;

import javax.swing.*;
import java.awt.*;

public class MessagingPage extends LUserPanel implements ServerAnswerInterpreter {
    private final NewChatButton newChatButton;
    private final ChatsPageButton chatsPageButton;
    private final SavedMessagesPageButton savedMessagesPageButton;
    private final NewGroupButton newGroupButton;
    private final GroupsButton groupsButton;
    private final SpreadButton spreadButton;
    private ChatsPage chatsPage;
    private GroupsPage groupsPage;

    public MessagingPage(MainMenuPage mainMenuPage) {
        super(mainMenuPage, mainMenuPage.getMainPanel(), mainMenuPage.getOwnerId());
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

        newChatButton = new NewChatButton(Config.getConfig("MenuButtonsPro"), this);
        newChatButton.setText("New Chat");
        newChatButton.setBounds(250, 120, 300, 50);
        this.add(newChatButton);

        chatsPageButton = new ChatsPageButton(Config.getConfig("MenuButtonsPro"), this);
        chatsPageButton.setText("Chats Page");
        chatsPageButton.setBounds(250, 190, 300, 50);
        this.add(chatsPageButton);

        savedMessagesPageButton = new SavedMessagesPageButton(Config.getConfig("MenuButtonsPro"), this);
        savedMessagesPageButton.setText("Saved Messages");
        savedMessagesPageButton.setBounds(250, 260, 300, 50);
        this.add(savedMessagesPageButton);

        newGroupButton = new NewGroupButton(Config.getConfig("MenuButtonsPro"), this);
        newGroupButton.setText("New Group");
        newGroupButton.setBounds(250, 330, 300, 50);
        this.add(newGroupButton);

        groupsButton = new GroupsButton(Config.getConfig("MenuButtonsPro"), this);
        groupsButton.setText("Groups");
        groupsButton.setBounds(250, 400, 300, 50);
        this.add(groupsButton);

        spreadButton = new SpreadButton(Config.getConfig("MenuButtonsPro"), this);
        spreadButton.setText("Spread");
        spreadButton.setBounds(250, 470, 300, 50);
        this.add(spreadButton);

        this.repaint();
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        this.interpretServerAnswer(logicalEvent);
    }

    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        try {
            DeclareNewChatMadeEvent declareNewChatMadeEvent = ((DeclareNewChatMadeEvent)serverEvent);
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (ClassCastException f){
            try {
                this.chatsPage = (new ChatsPage(this, ((SendUserChatsInfoEvent) serverEvent).getUserChatsInfo(), this));
                this.chatsPage.mShow();
            } catch (ClassCastException e){
                try {
                    this.groupsPage = new GroupsPage(this,((SendUserGroupsInfoEvent) serverEvent).getGroupsInfo(), this);
                    this.groupsPage.mShow();
                } catch (ClassCastException p){
                    try {
                        (new SavedMessagesPage(this, GraphicalTweet.buildGraphicalTweets(((SendUserSavedMessagesEvent) serverEvent).getTweets(), this))).mShow();
                    } catch (ClassCastException b){
                        try {
                            chatsPage.receiveServerEvent((GraphicalChatServerEvent) serverEvent);
                        } catch (ClassCastException c){
                            try {
                                groupsPage.receiveServerEvent((GraphicalGroupServerEvent)serverEvent);
                            } catch (ClassCastException h){
                                try {
                                    groupsPage.receiveServerEvent((SendGroupInfoServerEvent)serverEvent);
                                } catch (ClassCastException a){
                                    try {
                                        chatsPage.receiveServerEvent((SendChatInfoServerEvent)serverEvent);
                                    } catch (ClassCastException z){
                                        try {
                                            //System.out.println("{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}\n{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}\n{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}");
                                            this.chatsPage = (new ChatsPage(this, ((SendUserChatsWithBotsInfoEvent) serverEvent).getUserChatsInfo(), this));
                                            this.chatsPage.mShow();
                                        } catch (ClassCastException l){
                                            try {
                                                DeclareNewGroupMadeEvent declareNewGroupMadeEvent = ((DeclareNewGroupMadeEvent) serverEvent);
                                                JOptionPane.showMessageDialog(null, "Done!");
                                            }catch (ClassCastException x){
                                                System.out.println();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void openGroupPage(String token){
        try {
            this.sendClientEvent(new RequestUserGroupsEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        groupsPage.openGroupByToken(token);
    }

    public void openChatPage(String token){
        try {
            this.sendClientEvent(new RequestUserChatsInfoEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        chatsPage.openChatByToken(token);
    }

    public void openBotChatPage(String token){
        try {
            this.sendClientEvent(new RequestUserChatsWithBotsInfoEvent(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        chatsPage.openChatByToken(token);
    }
}
