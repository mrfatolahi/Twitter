package controller.botmanager;

import XLogger.MLogger;
import config.Config;
import controller.MainController;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.bots.BotEventCE;
import events.clientevents.main.ClientEvent;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;
import events.serverevents.main.ServerEvent;
import models.bots.GameBotGameInfo;
import models.bots.GameBotInfo;
import saversandloaders.savers.Saver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class GameBotsManager extends BotManager{
    private final GameBotInfo gameBotInfo;
    private boolean canCreateAGame;

    public GameBotsManager(BotController botController, GameBotInfo gameBotInfo) {
        super(botController);
        this.gameBotInfo = gameBotInfo;
        canCreateAGame = true;
        start();
    }

    @Override
    public void start() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                checkConfigFile();
            }
        })).start();
    }

    private String generateNewGameLink(int requesterId) throws IOException {
        String gameLink = MainController.generateRandomString();
        gameBotInfo.getWaitingPlayers().put(gameLink, requesterId);
        return gameLink;
    }

    private String startANewGame(String gameLink, int player2Id) throws Exception {
        int player1Id = gameBotInfo.getWaitingPlayers().get(gameLink);
        gameBotInfo.getWaitingPlayers().remove(gameLink);
        GameBotGameInfo game = new GameBotGameInfo(player1Id, player2Id);

        gameBotInfo.getGamesInfo().add(game);
        String startingInfo = (String) botClass.getMethod("manageGame", new Class[]{int.class}).invoke(null, 0);

        return startingInfo;
    }

    private String hitASquare(int hiterId, int numberOfSquare) throws Exception{
        for (GameBotGameInfo gameBotGameInfo : gameBotInfo.getGamesInfo()) {
            if (gameBotGameInfo.getPlayer1Id() == hiterId || gameBotGameInfo.getPlayer2Id() == hiterId){
                String position = (String) botClass.getMethod("manageGame", new Class[]{int.class}).invoke(null, numberOfSquare);
                return position;
            }
        }
        return null;
    }

    private void checkGameFinish(GameBotGameInfo gameBotGameInfo, String position){
        int status = Character.getNumericValue(position.charAt(0));
        if (status == 0 || status == 3 || status == 4){
            gameBotInfo.getGamesInfo().remove(gameBotGameInfo);
        }
    }

    public int getOpponentId(int id){
        for (GameBotGameInfo gameInfo : gameBotInfo.getGamesInfo()){
            if (gameInfo.getPlayer1Id() == id){
                return gameInfo.getPlayer2Id();
            } else
            if (gameInfo.getPlayer2Id() == id){
                return gameInfo.getPlayer1Id();
            }
        }
        return -1;
    }

    @Override
    public String receiveNewCommand(String command, int commanderId) throws Exception {
        if (command.equals("GENERATE_A_LINK")){
            if (!canCreateAGame){
                return "Can't response";
            }
            return generateNewGameLink(commanderId);
        } else
        if (command.contains("START_NEW_GAME")){
            if (!canCreateAGame){
                return "Can't response";
            }
            canCreateAGame = false;
            String gameLink = "";
            for (int i = 0; i < command.length(); i++) {
                if (command.charAt(i) == '\n'){
                    for (int j = i+1; j < command.length(); j++) {
                        gameLink = gameLink + command.charAt(j);
                    }
                    break;
                }
            }
            return startANewGame(gameLink, commanderId);
        } else{
            return hitASquare(commanderId, Integer.parseInt(command));
        }
    }

    @Override
    protected void checkConfigFile(){
        while (true){
            try {
                Thread.sleep(10000);
                String address = Config.getConfig("BotsURLs").getProperty("newGameBotURL");
                if (receivedURLs.contains(address)) {
                    continue;
                }
                receivedURLs.add(address);
                URLClassLoader urlClassLoader;
                urlClassLoader = new URLClassLoader(new URL[]{new URL(address)});

                botClass = urlClassLoader.loadClass("SGame");
                System.out.println("Bot Loaded");
                MLogger.log("INFO: GameBot Loaded");
                busy = false;
                canCreateAGame = false;
            } catch (Exception e){
                busy = true;
                canCreateAGame = true;
                MLogger.log("WARN: Can't load GameBot class");
            }
        }
    }


    public Class getBotClass() {
        return botClass;
    }

    public void setBotClass(Class botClass) {
        this.botClass = botClass;
    }

    public GameBotInfo getGameBotInfo() {
        return gameBotInfo;
    }

    public boolean isCanCreateAGame() {
        return canCreateAGame;
    }

    public void setCanCreateAGame(boolean canCreateAGame) {
        this.canCreateAGame = canCreateAGame;
    }

    @Override
    public void addFollower(int id) throws IOException {
        gameBotInfo.getFollowersId().add(id);
        Saver.saveGameBotInfo(gameBotInfo);
    }

    @Override
    protected <E extends BotEventCE> void interpreteReceivedEvent(E event) {

    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {

    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteReceivedEvent((BotEventCE) clientEvent);
    }

}
