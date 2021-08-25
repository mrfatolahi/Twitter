package view.notloggeduser;

import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import view.mainclasses.GraphicalAgent;
import view.mainclasses.MainPanel;
import view.notloggeduser.wellcomepage.WellComePage;

public class NLUserGraphicalAgent implements EventManager {
    private final GraphicalAgent graphicalAgent;
    private final MainPanel mainPanel;
    private final WellComePage wellComePage;

    public NLUserGraphicalAgent(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
        this.mainPanel = graphicalAgent.getMainPanel();
        this.wellComePage = new WellComePage(this, mainPanel);
    }

    public void openAWellComePage(){
        mainPanel.removeAll();
        mainPanel.add(new WellComePage(this, mainPanel));
        mainPanel.repaint();
    }

    public GraphicalAgent getGraphicalAgent() {
        return graphicalAgent;
    }

    public MainPanel getMainFrame() {
        return mainPanel;
    }

    public GraphicalAgent getGraphAgent() {
        return graphicalAgent;
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        graphicalAgent.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        wellComePage.receiveServerEvent(logicalEvent);
    }
}
