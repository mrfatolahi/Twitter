package view.loggeduser.graphicalmodels.graphicaltweet.model;

import config.Config;
import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import view.mainclasses.GraphicalAgent;
import view.loggeduser.graphicalmodels.graphicaltweet.buttons.GraphicalTweetButton;
import view.loggeduser.graphicalmodels.graphicaltweet.listeners.*;
import models.tweet.Tweet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicalTweet extends JPanel implements EventManager {
    private final EventManager eventManager;
    private final Tweet tweet;
    private JLabel userProfileLabel;
    private JLabel textLabel;
    private JLabel imageLabel;
    private JLabel userNameLabel;
    private JLabel dateTimeCreatedLabel;
    private GraphicalTweetButton commentButton;
    private JLabel commentsNumberLabel;
    private GraphicalTweetButton retweetButton;
    private JLabel retweetsNumberLabel;
    private GraphicalTweetButton likeButton;
    private JLabel likesNumberLabel;
    private GraphicalTweetButton forwardButton;
    private GraphicalTweetButton optionsButton;
    private GraphicalTweetButton hyperLinkButton;
    private JLabel text;

    public GraphicalTweet(EventManager eventManager, Tweet tweet) throws Exception {
        this.eventManager = eventManager;
        this.tweet = tweet;
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setMaximumSize(new Dimension(600, 360));

        this.userProfileLabel = new JLabel();
        this.userProfileLabel.setIcon(GraphicalAgent.reSizeImage(50, 50, tweet.getUserProfileImagePath()));
        this.userProfileLabel.setBounds(0, 0, 50, 50);
        this.userProfileLabel.repaint();
        this.add(userProfileLabel);

        this.textLabel = new JLabel(tweet.getText());
        this.textLabel.setBounds(0, 70, 600, 20);
        this.add(textLabel);

        Config buttonsConfig = Config.getConfig("GraphicalTweerButton");

        this.imageLabel = new JLabel();
        if (tweet.getImagePath() == null || tweet.getImagePath().equals("")){
            this.imageLabel.setIcon(GraphicalAgent.reSizeImage(600, 200, Config.getConfig("ImagesPro").getProperty("NoImage")));
        } else {
            this.imageLabel.setIcon(GraphicalAgent.reSizeImage(600, 200, tweet.getImagePath()));
        }

        this.imageLabel.setBounds(0, 80, 600, 250);
        this.add(imageLabel);

        this.userNameLabel = new JLabel(tweet.getUserUsername());
        this.userNameLabel.setBounds(60, 5, 100, 25);
        this.add(userNameLabel);

        this.dateTimeCreatedLabel = new JLabel(tweet.getDateAndTimeCreated().toString());
        this.dateTimeCreatedLabel.setBounds(60, 30, 100 , 20);
        this.add(dateTimeCreatedLabel);

        this.commentButton = new GraphicalTweetButton(buttonsConfig, this);
        this.commentButton.setBackground(Color.WHITE);
        this.commentButton.setIcon(GraphicalAgent.reSizeImage(20, 20, buttonsConfig.getProperty("CommentIcon")));
        this.commentButton.setBorderPainted(false);
        this.commentButton.addMouseListener(new CommentButtomListener(commentButton));
        this.commentButton.setBounds(60, 335, 25, 25);
        this.add(commentButton);

        this.commentsNumberLabel = new JLabel(String.valueOf(tweet.getComments().size()));
        this.commentsNumberLabel.setBounds(90, 335, 25, 25);
        this.add(commentsNumberLabel);

        this.likeButton = new GraphicalTweetButton(buttonsConfig, this);
        this.likeButton.setIcon(GraphicalAgent.reSizeImage(20, 20, buttonsConfig.getProperty("LikeIcon")));
        this.likeButton.setBorderPainted(false);
        this.likeButton.addMouseListener(new LikeButtonListener(likeButton));
        this.likeButton.setBounds(220, 335, 25, 25);
        this.add(likeButton);

        this.likesNumberLabel = new JLabel(String.valueOf(tweet.getLikes().size()));
        this.likesNumberLabel.setBounds(250, 335, 25, 25);
        this.add(likesNumberLabel);

        this.retweetButton = new GraphicalTweetButton(buttonsConfig, this);
        this.retweetButton.setIcon(GraphicalAgent.reSizeImage(20, 20, buttonsConfig.getProperty("RetweetIcon")));
        this.retweetButton.addMouseListener(new RetweetButtonListener(retweetButton));
        this.retweetButton.setBorderPainted(false);
        this.retweetButton.setBounds(380, 335, 25, 25);
        this.add(retweetButton);

        this.retweetsNumberLabel = new JLabel(String.valueOf(tweet.getRetweets().size()));
        this.retweetsNumberLabel.setBounds(410, 335, 25, 25);
        this.add(retweetsNumberLabel);

        this.forwardButton = new GraphicalTweetButton(buttonsConfig, this);
        this.forwardButton.setIcon(GraphicalAgent.reSizeImage(20, 20, buttonsConfig.getProperty("ShareIcon")));
        this.forwardButton.addMouseListener(new ForwardButtonListener(forwardButton));
        this.forwardButton.setBorderPainted(false);
        this.forwardButton.setBounds(540, 335, 25, 25);
        this.add(forwardButton);

        this.optionsButton = new GraphicalTweetButton(buttonsConfig, this);
        this.optionsButton.setIcon(GraphicalAgent.reSizeImage(30, 30, buttonsConfig.getProperty("OptionsIcon")));
        this.optionsButton.addMouseListener(new OptionsButtonListener(optionsButton));
        this.optionsButton.setBorderPainted(false);
        this.optionsButton.setBounds(550, 20, 35, 35);
        this.add(optionsButton);

        if (tweet.getHyperlink() != null){
            this.hyperLinkButton = new GraphicalTweetButton(buttonsConfig, this);
            this.hyperLinkButton.setText(this.getTweet().getHyperlink().getText());
            this.hyperLinkButton.setForeground(Color.BLUE.darker());
            this.hyperLinkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.hyperLinkButton.addMouseListener(new HyperLinkButtonListener(hyperLinkButton));
            this.hyperLinkButton.setBorderPainted(false);
            this.hyperLinkButton.setBackground(Color.WHITE);
            this.hyperLinkButton.setBounds(250, 5, 200, 50);
            this.add(hyperLinkButton);
        }

        this.repaint();
    }


    public Tweet getTweet() {
        return tweet;
    }

    public JLabel getUserProfileLabel() {
        return userProfileLabel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public JLabel getUserNameLabel() {
        return userNameLabel;
    }

    public JLabel getDateTimeCreatedLabel() {
        return dateTimeCreatedLabel;
    }

    public GraphicalTweetButton getCommentButton() {
        return commentButton;
    }

    public GraphicalTweetButton getRetweetButton() {
        return retweetButton;
    }

    public GraphicalTweetButton getLikeButton() {
        return likeButton;
    }

    public GraphicalTweetButton getForwardButton() {
        return forwardButton;
    }

    public JLabel getText() {
        return text;
    }

    public static GraphicalTweet buildGraphicalTweet(EventManager eventManager, Tweet tweet) throws Exception {
        return new GraphicalTweet(eventManager, tweet);
    }

    public static ArrayList<GraphicalTweet> buildGraphicalTweets(ArrayList<Tweet> tweets, EventManager eventManager) {
        ArrayList<GraphicalTweet> graphicalTweets = new ArrayList<>();
        for (Tweet tweet : tweets){
            try {
                graphicalTweets.add(buildGraphicalTweet(eventManager, tweet));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return graphicalTweets;
    }

    public JLabel getTextLabel() {
        return textLabel;
    }

    public JLabel getCommentsNumberLabel() {
        return commentsNumberLabel;
    }

    public JLabel getRetweetsNumberLabel() {
        return retweetsNumberLabel;
    }

    public JLabel getLikesNumberLabel() {
        return likesNumberLabel;
    }

    public GraphicalTweetButton getOptionsButton() {
        return optionsButton;
    }

    public EventManager getGraphicEventManager() {
        return eventManager;
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        eventManager.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws IOException {

    }
}
