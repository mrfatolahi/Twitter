package view.loggeduser.mainmenupage.explorerpage;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage.RequestHotTweetsEvent;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import view.loggeduser.LUserPanel;
import view.loggeduser.graphicalmodels.graphicaltweet.model.GraphicalTweet;
import models.tweet.Tweet;
import view.mainclasses.Updatable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HotTweetsPage extends LUserPanel implements ServerAnswerInterpreter, Updatable {
    private ArrayList<GraphicalTweet> tweets;
    private Thread updaterThread;


    public HotTweetsPage(ExplorerPage explorerPage, ArrayList<Tweet> tweets) {
        super(explorerPage, explorerPage.getMainPanel(), explorerPage.getOwnerId());

        this.tweets = (GraphicalTweet.buildGraphicalTweets(tweets, this));
        this.setLayout(null);
        this.setBackground(Color.YELLOW);
        this.setBounds(0, 0, 800, 720);
        JPanel tweetsPanel = new JPanel(null);
        tweetsPanel.setPreferredSize(new Dimension(600, tweets.size()*360));

        int height = 0;

        for (GraphicalTweet tweet : this.tweets){
            tweet.setBounds(0, height, 600, 360);
            tweetsPanel.add(tweet);
            height += 360;
        }
        tweetsPanel.repaint();

        JScrollPane scrollPane = new JScrollPane(tweetsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
        scrollBar.setUnitIncrement(36);
        scrollPane.setVerticalScrollBar(scrollBar);

        scrollPane.setBounds(0, 0, 620, 720);
        scrollPane.validate();
        scrollPane.repaint();
        this.add(scrollPane);

        manageUpdates();
        this.repaint();
        this.revalidate();
    }


    public ArrayList<GraphicalTweet> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<GraphicalTweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public void back() {
        updaterThread.interrupt();
        super.back();
    }

    @Override
    public void manageUpdates() {
        updaterThread = (new Thread(() -> {
            try {
                Thread.sleep(5000);
                sendClientEvent(new RequestHotTweetsEvent(), true);
                System.out.println("UPDATED!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        updaterThread.start();
    }
}
