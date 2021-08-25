package view.loggeduser;

import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.luser.graphicalmodels.graphicalprofile.FollowingResultEvent;
import events.serverevents.main.ServerEvent;
import view.loggeduser.mainmenupage.MainMenuPage;
import view.mainclasses.*;

import javax.swing.*;

public class LUserGraphicalAgent implements EventManager, ServerAnswerInterpreter {
    private final int ownerId;
    private final GraphicalAgent graphicalAgent;
    private final MainMenuPage mainMenuPage;

    public LUserGraphicalAgent(int ownerId, MainPanel mainPanel, GraphicalAgent graphicalAgent) {
        this.ownerId = ownerId;
        this.graphicalAgent = graphicalAgent;
        this.mainMenuPage= new MainMenuPage(this, mainPanel, ownerId);
    }

    public int getOwnerId() {
        return ownerId;
    }

    public GraphicalAgent getGraphAgent() {
        return graphicalAgent;
    }

    public void tryToReconnect(){
        graphicalAgent.tryToReconnect();
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        graphicalAgent.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        if (logicalEvent.getClass() == FollowingResultEvent.class){
            interpretServerAnswer(logicalEvent);
            return;
        }
        mainMenuPage.receiveServerEvent(logicalEvent);
    }

    public void openAWellComePage(){
        graphicalAgent.openAWellComePage();
    }

    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception {
        switch (((FollowingResultEvent) serverEvent).getFollowingResult()){
            case DONE:
                JOptionPane.showMessageDialog(null, "Done!");
                break;
            case FOLLOWING_REQUEST_SENT:
                JOptionPane.showMessageDialog(null, "Account is private, Your following request has been sent.");
                break;
            case USER_HAS_BLOCKED_REQUESTER:
                JOptionPane.showMessageDialog(null, "You can't follow this user because he/she has blocked you.");
                break;
            case ALREADY_FOLLOWED:
                JOptionPane.showMessageDialog(null, "You already followed this user.");
                break;
        }
    }
}
