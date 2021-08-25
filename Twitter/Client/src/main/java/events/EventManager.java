package events;

import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;

public interface EventManager {
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception;

    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception;
}
