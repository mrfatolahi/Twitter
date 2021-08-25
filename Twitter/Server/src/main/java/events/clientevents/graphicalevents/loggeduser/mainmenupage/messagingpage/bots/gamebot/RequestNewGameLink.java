package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.bots.gamebot;

public class RequestNewGameLink extends GameBotEventCE{
    private final int requesterId;

    public RequestNewGameLink(int requesterId) {
        this.requesterId = requesterId;
    }

    public int getRequesterId() {
        return requesterId;
    }
}
