package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.bots.gamebot;

public class SendNewGameLinkCE extends GameBotEventCE{
    private final int senderId;
    private final String gameLink;

    public SendNewGameLinkCE(int senderId, String gameLink) {
        this.senderId = senderId;
        this.gameLink = gameLink;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getGameLink() {
        return gameLink;
    }
}
