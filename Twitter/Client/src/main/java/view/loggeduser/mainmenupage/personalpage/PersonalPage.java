package view.loggeduser.mainmenupage.personalpage;

import config.Config;
import events.EventManager;
import events.serverevents.luser.personalpage.infopage.SendUserInfoForPP;
import events.serverevents.luser.personalpage.listspage.ListsPageServerEvent;
import events.serverevents.luser.personalpage.notificationspage.NotificationsPageServerEvent;
import events.serverevents.luser.personalpage.tweetspage.SendUserTweetsEvent;
import events.serverevents.main.ServerEvent;
import events.serverevents.luser.*;
import view.mainclasses.GraphicalAgent;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicaltweet.model.GraphicalTweet;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.loggeduser.mainmenupage.personalpage.NotificationsPage.NotificationsPage;
import view.loggeduser.mainmenupage.personalpage.buttons.*;
import view.loggeduser.mainmenupage.personalpage.buttons.NotificationsPageButton;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePage;
import view.loggeduser.mainmenupage.personalpage.listspage.ListsPage;

import javax.swing.*;
import java.awt.*;

public class PersonalPage extends LUserPanel implements EventManager {
    private TweetsPage tweetsPage;
    private EditProfilePage editProfilePage;
    private ListsPage listsPage;
    private NotificationsPage notificationsPage;
    private final NewTweetButton newTweetButton;
    private final TweetsPageButton TweetsPageButton;
    private final EditProfilePageButton editProfilePageButton;
    private final ListsPageButton listsPageButton;
    private final InfoButton infoButton;
    private final NotificationsPageButton notificationsPageButton;

    public PersonalPage(MainMenuPage mainMenuPage) throws Exception {
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

        newTweetButton = new NewTweetButton(Config.getConfig("MenuButtonsPro"), this);
        newTweetButton.setText("New Tweet");
        newTweetButton.setBounds(250, 120, 300, 50);
        this.add(newTweetButton);

        TweetsPageButton = new TweetsPageButton(Config.getConfig("MenuButtonsPro"), this);
        TweetsPageButton.setText("Tweets Page");
        TweetsPageButton.setBounds(250, 190, 300, 50);
        this.add(TweetsPageButton);

        editProfilePageButton = new EditProfilePageButton(Config.getConfig("MenuButtonsPro"), this);
        editProfilePageButton.setText("Edit Profile");
        editProfilePageButton.setBounds(250, 260, 300, 50);
        this.add(editProfilePageButton);

        listsPageButton = new ListsPageButton(Config.getConfig("MenuButtonsPro"), this);
        listsPageButton.setText("Lists Page");
        listsPageButton.setBounds(250, 330, 300, 50);
        this.add(listsPageButton);

        infoButton = new InfoButton(Config.getConfig("MenuButtonsPro"), this);
        infoButton.setText("Info");
        infoButton.setBounds(250, 400, 300, 50);
        this.add(infoButton);

        notificationsPageButton = new NotificationsPageButton(Config.getConfig("MenuButtonsPro"), this);
        notificationsPageButton.setText("Notifications");
        notificationsPageButton.setBounds(250, 470, 300, 50);
        this.add(notificationsPageButton);

    }

    public ListsPage getListsPage() {
        return listsPage;
    }

    public TweetsPage getTweetsPage() {
        return tweetsPage;
    }

    public EditProfilePage getEditProfilePage() {
        return editProfilePage;
    }

    public NotificationsPage getNotificationsPage() {
        return notificationsPage;
    }

    public void setTweetsPage(TweetsPage tweetsPage) {
        this.tweetsPage = tweetsPage;
    }

    public void setEditProfilePage(EditProfilePage editProfilePage) {
        this.editProfilePage = editProfilePage;
    }

    public void setListsPage(ListsPage listsPage) {
        this.listsPage = listsPage;
    }

    public void setNotificationsPage(NotificationsPage notificationsPage) {
        this.notificationsPage = notificationsPage;
    }

    private void showUserInfo(SendUserInfoForPP sendUserInfoForPP){
        JOptionPane.showMessageDialog(this,
                "Name: "+sendUserInfoForPP.getName()+"\n"+
                        "Lastname: "+sendUserInfoForPP.getLastname()+"\n"+
                        "Username: "+sendUserInfoForPP.getUsername()+"\n"+
                        "Birthdate: "+sendUserInfoForPP.getBirthdate()+"\n"+
                        "Email: "+sendUserInfoForPP.getEmail()+"\n"+
                        "Phone Number: "+sendUserInfoForPP.getPhonenumber()+"\n"+
                        "Bio: "+sendUserInfoForPP.getBio()+"\n",
                "Your Info Are:", JOptionPane.INFORMATION_MESSAGE, GraphicalAgent.reSizeImage(200, 200, sendUserInfoForPP.getProfileImagePath()));
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        try {
            listsPage.receiveServerEvent((ListsPageServerEvent) logicalEvent);
        } catch (ClassCastException e){
            try {
                notificationsPage.receiveServerEvent((NotificationsPageServerEvent) logicalEvent);
            } catch (ClassCastException l){
                try {
                    showUserInfo((SendUserInfoForPP) logicalEvent);
                } catch (Exception r){
                    try {
                        (new TweetsPage(this, GraphicalTweet.buildGraphicalTweets(((SendUserTweetsEvent)logicalEvent).getTweets(), this))).mShow();
                    } catch (Exception q){
                        System.out.println();
                    }
                }
            }
        }
    }
}
