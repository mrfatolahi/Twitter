package events.serverevents.interfaces;


import events.serverevents.main.ServerEvent;

public interface ServerAnswerInterpreter {
    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) throws Exception;
}
