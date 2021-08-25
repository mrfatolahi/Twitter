package view.loggeduser.mainmenupage.messagingpage;

import events.EventManager;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicaltweet.model.GraphicalTweet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SavedMessagesPage extends LUserPanel implements EventManager {
    private final ArrayList<GraphicalTweet> tweets;

    public SavedMessagesPage(MessagingPage messagingPage, ArrayList<GraphicalTweet> tweets) {
        super(messagingPage, messagingPage.getMainPanel(), messagingPage.getOwnerId());
        System.out.println(tweets.get(0).getTweet().getText());
        System.out.println(tweets.size());
        this.setLayout(null);
        this.tweets = tweets;
        this.setBackground(Color.YELLOW);
        this.setBounds(0, 0, 800, 720);

        JPanel tweetsPanel = new JPanel(null);
        tweetsPanel.setPreferredSize(new Dimension(600, tweets.size()*360));

        int height = 0;

        tweetsPanel.setBackground(Color.BLUE);
        for (GraphicalTweet tweet : tweets){
            tweet.setBounds(0, height, 600, 360);
            tweetsPanel.add(tweet);
            height += 360;
        }
        tweetsPanel.repaint();

        JScrollPane scrollPane = new JScrollPane(tweetsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
        scrollBar.setUnitIncrement(36);
        scrollPane.setVerticalScrollBar(scrollBar);

        if (tweets.size() == 1){
            scrollPane.setBounds(0, 0, 620, 360);
        } else {
            scrollPane.setBounds(0, 0, 620, 720);
        }
        scrollPane.validate();
        scrollBar.repaint();
        this.add(scrollPane);

        this.repaint();
        this.revalidate();

    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws IOException {

    }
}
