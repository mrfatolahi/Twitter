package events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.bots.gamebot;

public class HitASquareEvent extends GameBotEventCE{
    private final int hiterId;
    private final int numberOfSquare;

    public HitASquareEvent(int hiterId, int numberOfSquare) {
        this.hiterId = hiterId;
        this.numberOfSquare = numberOfSquare;
    }

    public int getHiterId() {
        return hiterId;
    }

    public int getNumberOfSquare() {
        return numberOfSquare;
    }
}
