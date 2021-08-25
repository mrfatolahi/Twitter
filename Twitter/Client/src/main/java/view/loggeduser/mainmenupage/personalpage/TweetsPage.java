package view.loggeduser.mainmenupage.personalpage;

import events.EventManager;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicaltweet.model.GraphicalTweet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TweetsPage extends LUserPanel implements EventManager {
    private final ArrayList<GraphicalTweet> tweets;

    public TweetsPage(PersonalPage personalPage, ArrayList<GraphicalTweet> tweets) {
        super(personalPage, personalPage.getMainPanel(), personalPage.getOwnerId());
        this.setLayout(null);
        this.tweets = tweets;
        this.setBackground(Color.YELLOW);
        this.setBounds(0, 0, 800, 740);

        JPanel tweetsPanel = new JPanel(null);
        tweetsPanel.setPreferredSize(new Dimension(600, tweets.size()*380));

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
            scrollPane.setBounds(0, 0, 620, 740);
        }
        scrollPane.validate();
        scrollBar.repaint();
        this.add(scrollPane);

        this.repaint();
        this.revalidate();
        System.out.println("?Z?Z?Z?Z");
    }




    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws IOException {

    }
}
