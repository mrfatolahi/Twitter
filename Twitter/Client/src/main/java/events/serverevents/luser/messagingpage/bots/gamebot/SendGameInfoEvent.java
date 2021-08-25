package events.serverevents.luser.messagingpage.bots.gamebot;

public class SendGameInfoEvent extends GameBotServerEvent {
    private final int turnAndResultStatus;
    private final String boardInfo;

    public SendGameInfoEvent(String rawInfo) {
        turnAndResultStatus = Character.getNumericValue(rawInfo.charAt(0));
        boardInfo = rawInfo.substring(2);
    }

    public int getTurnAndResultStatus() {
        return turnAndResultStatus;
    }

    public String getBoardInfo() {
        return boardInfo;
    }
}
