package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.*;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;

import java.io.IOException;

public class GraphicalTweetEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public GraphicalTweetEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <G extends GraphicalTweetEvent> void interpreteGraphicalTweetEvent(G graphicalTweetEvent) throws IOException {
        if (graphicalTweetEvent.getClass() == NewCommentEvent.class){
            mainInterPreter.getlUserLogicalAgent().commentTweet(((NewCommentEvent) graphicalTweetEvent).getTweet(), ((NewCommentEvent) graphicalTweetEvent).getText());
        } else
        if (graphicalTweetEvent.getClass() == NewLikeEvent.class){
            mainInterPreter.getlUserLogicalAgent().likeTweet(((NewLikeEvent) graphicalTweetEvent).getTweet());
        } else
        if (graphicalTweetEvent.getClass() == TweetForwardEvent.class){
            mainInterPreter.getlUserLogicalAgent().forwardTweet(((TweetForwardEvent) graphicalTweetEvent).getTweet(), ((TweetForwardEvent) graphicalTweetEvent).getReceiverUsername());
        } else
        if (graphicalTweetEvent.getClass() == RetweetEvent.class){
            mainInterPreter.getlUserLogicalAgent().retweetTweet(((RetweetEvent) graphicalTweetEvent).getTweet(), ((RetweetEvent) graphicalTweetEvent).getText(), ((RetweetEvent) graphicalTweetEvent).getTags(), ((RetweetEvent) graphicalTweetEvent).getImagePath());
        } else
        if (graphicalTweetEvent.getClass() == BlockTweetOwnerEvent.class){
            mainInterPreter.getlUserLogicalAgent().blockThis(((BlockTweetOwnerEvent) graphicalTweetEvent).getTweet().getUserId());
        } else
        if (graphicalTweetEvent.getClass() == MuteTweetOwnerEvent.class){
            mainInterPreter.getlUserLogicalAgent().muteThis(((MuteTweetOwnerEvent) graphicalTweetEvent).getTweet().getUserId());
        } else
        if (graphicalTweetEvent.getClass() == ReportTweetEvent.class){
            mainInterPreter.getlUserLogicalAgent().reportTweet(((ReportTweetEvent) graphicalTweetEvent).getTweet());
        } else
        if (graphicalTweetEvent.getClass() == AddTweetToSavedMessages.class){
            mainInterPreter.getlUserLogicalAgent().addTweetToSomeBodySavedMessages(((AddTweetToSavedMessages) graphicalTweetEvent).getViewerId(), ((AddTweetToSavedMessages) graphicalTweetEvent).getTweet());
        }
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteGraphicalTweetEvent((GraphicalTweetEvent) clientEvent);
    }
}
