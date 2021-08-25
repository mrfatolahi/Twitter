package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile.*;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;
import saversandloaders.loaders.UserLoader;

import java.io.IOException;

public class GraphicalProfileEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public GraphicalProfileEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <A extends GraphicalProfileEvent> void interpreteGraphicalProfileEvent(A graphicalProfileEvent) throws IOException, InterruptedException {
        if (graphicalProfileEvent.getClass() == FollowSomeBodyEvent.class){
            manageFollowSomeBodyEvent(((FollowSomeBodyEvent) graphicalProfileEvent));
        } else
        if (graphicalProfileEvent.getClass() == UnFollowSomeBodyEvent.class){
            manageUnFollowSomeBodyEvent(((UnFollowSomeBodyEvent) graphicalProfileEvent));
        } else
        if (graphicalProfileEvent.getClass() == BlockSomeBodyEvent.class){
            mainInterPreter.getlUserLogicalAgent().blockThis(UserLoader.loadUser((((BlockSomeBodyEvent) graphicalProfileEvent).getUserToBlockId())));
        } else
        if (graphicalProfileEvent.getClass() == UnBlockSomeBodyEvent.class){
            mainInterPreter.getlUserLogicalAgent().unblockThis(UserLoader.loadUser((((UnBlockSomeBodyEvent) graphicalProfileEvent).getUserToUnBlockId())));
        }
    }

    private void manageFollowSomeBodyEvent(FollowSomeBodyEvent followSomeBodyEvent) throws IOException {
        if (followSomeBodyEvent.getUserToFollowId() == -10 || followSomeBodyEvent.getUserToFollowId() == -20 || followSomeBodyEvent.getUserToFollowId() == -30){
            mainInterPreter.getlUserLogicalAgent().followBot(followSomeBodyEvent.getUserToFollowId());
            return;
        }
        mainInterPreter.getlUserLogicalAgent().followThis(UserLoader.loadUser(((followSomeBodyEvent).getUserToFollowId())));
    }

    private void manageUnFollowSomeBodyEvent(UnFollowSomeBodyEvent unFollowSomeBodyEvent) throws IOException {
        if (unFollowSomeBodyEvent.getUserToUnFollowId() == -10 || unFollowSomeBodyEvent.getUserToUnFollowId() == -20 || unFollowSomeBodyEvent.getUserToUnFollowId() == -30){
            mainInterPreter.getlUserLogicalAgent().unFollowBot(unFollowSomeBodyEvent.getUserToUnFollowId());
            return;
        }
        mainInterPreter.getlUserLogicalAgent().unfollowThis(UserLoader.loadUser(((unFollowSomeBodyEvent).getUserToUnFollowId())));
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteGraphicalProfileEvent((GraphicalProfileEvent) clientEvent);
    }
}
