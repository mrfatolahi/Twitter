package events.serverevents.luser.messagingpage.bots.gamebot;

public class SendNewGameLinkSE extends GameBotServerEvent{
    private final String gameLink;

    public SendNewGameLinkSE(String gameLink) {
        this.gameLink = gameLink;
    }

    public String getGameLink() {
        return gameLink;
    }
}
