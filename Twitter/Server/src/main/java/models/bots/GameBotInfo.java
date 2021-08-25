package models.bots;

import events.serverevents.luser.explorerpage.UserInfoServerEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBotInfo extends BotInfo {
    private final HashMap<String, Integer> waitingPlayers;
    private final ArrayList<GameBotGameInfo> gamesInfo;

    public GameBotInfo() {
        super("GameBot");
        waitingPlayers = new HashMap<>();
        gamesInfo = new ArrayList<>();
    }

    public GameBotInfo(ArrayList<Integer> followersId) {
        super("GameBot", followersId);
        waitingPlayers = new HashMap<>();
        gamesInfo = new ArrayList<>();
    }

    public HashMap<String, Integer> getWaitingPlayers() {
        return waitingPlayers;
    }

    public ArrayList<GameBotGameInfo> getGamesInfo() {
        return gamesInfo;
    }

    @Override
    public UserInfoServerEvent generateBotInfoAsEvent(){
        return new UserInfoServerEvent
                (
                        -10, followersId.size(), null, -1,
                        "Game:", "XO", "GameBot",
                        "Game bot, it presents a simple XO game.",
                        null, false, true
                );
    }
}
