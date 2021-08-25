package controller.offlineController;


import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;

import java.io.IOException;

public interface LogicEventManager {

    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException;

    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception;
}
