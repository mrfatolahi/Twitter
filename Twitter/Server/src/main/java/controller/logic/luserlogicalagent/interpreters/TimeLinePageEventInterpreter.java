package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.timelinepage.TimeLinePageEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.luser.timelinepage.SendTimeLineTweetsEvent;
import events.serverevents.main.ServerEvent;

import java.io.IOException;

public class TimeLinePageEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public TimeLinePageEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <N extends TimeLinePageEvent> void interpreteTimeLinePageEvent(N timeLinePageEvent) throws IOException {
        sendLogicalEvent(new SendTimeLineTweetsEvent(mainInterPreter.getlUserLogicalAgent().getTimelineTweets()));
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteTimeLinePageEvent((TimeLinePageEvent) clientEvent);
    }
}
