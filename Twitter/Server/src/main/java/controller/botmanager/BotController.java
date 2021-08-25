package controller.botmanager;

import XLogger.MLogger;
import controller.ClientHandler;
import controller.MainController;
import models.bots.CalculatorBotInfo;
import models.bots.GameBotInfo;
import models.bots.QuestionBotInfo;
import saversandloaders.loaders.Loader;

public class BotController {
    private final MainController mainController;
    private final GameBotsManager gameBotsManager;
    private final QuestionBotManager questionBotManager;
    private final CalculatorBotManager calculatorBotManager;

    public BotController(MainController mainController) {
        this.mainController = mainController;
        GameBotsManager gameBotsManager1;
        try {
            gameBotsManager1 = new GameBotsManager(this, Loader.loadGameBotInfo());
        }catch (Exception e){
            e.printStackTrace();
            MLogger.log("ERORR: Can't load gameBot info");
            gameBotsManager1 = new GameBotsManager(this, new GameBotInfo());
        }
        this.gameBotsManager = gameBotsManager1;

        QuestionBotManager questionBotManager1;
        try {
            questionBotManager1 = new QuestionBotManager(this, Loader.loadQuestionBotInfo());
        } catch (Exception e){
            e.printStackTrace();
            MLogger.log("ERORR: Can't load questionBot info");
            questionBotManager1 = new QuestionBotManager(this, new QuestionBotInfo());
        }
        this.questionBotManager = questionBotManager1;

        CalculatorBotManager calculatorBotManager1;
        try {
            calculatorBotManager1 = new CalculatorBotManager(this, Loader.loadCalculatorBotInfo());
        } catch (Exception e){
            e.printStackTrace();
            MLogger.log("ERORR: Can't load calculatorBot info");
            calculatorBotManager1 = new CalculatorBotManager(this, new CalculatorBotInfo());
        }
        this.calculatorBotManager = calculatorBotManager1;
    }

    public MainController getMainController() {
        return mainController;
    }

    public GameBotsManager getGameBotsManager() {
        return gameBotsManager;
    }

    public ClientHandler getClientById(int id){
        return mainController.getClientById(id);
    }

    public QuestionBotManager getQuestionBotManager() {
        return questionBotManager;
    }

    public CalculatorBotManager getCalculatorBotManager() {
        return calculatorBotManager;
    }
}
