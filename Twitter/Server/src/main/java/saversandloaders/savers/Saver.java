package saversandloaders.savers;

import XLogger.MLogger;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import config.Config;
import models.bots.CalculatorBotInfo;
import models.bots.GameBotInfo;
import models.bots.QuestionBotInfo;
import saversandloaders.database.dbsavers.DBSaver;
import saversandloaders.json.jsonsavers.JSaver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    public static void saveGameBotInfo(GameBotInfo gameBotInfo) throws IOException {
        MLogger.log("INFO: Saver.saveGameBotInfo");
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            JSaver.jSaveGameBotInfo(gameBotInfo);
        } else {
            DBSaver.saveBotInfo(gameBotInfo);
        }
    }

    public static void saveQuestionBotInfo(QuestionBotInfo questionBotInfo) throws IOException {
        MLogger.log("INFO: Saver.saveQuestionBotInfo");
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            JSaver.jSaveQuestionBotInfo(questionBotInfo);
        } else {
            DBSaver.saveBotInfo(questionBotInfo);
        }
    }

    public static void saveCalculatorBotInfo(CalculatorBotInfo calculatorBotInfo) throws IOException {
        MLogger.log("INFO: Saver.saveCalculatorBotInfo");
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            JSaver.jSaveCalculatorBotInfo(calculatorBotInfo);
        } else {
            DBSaver.saveBotInfo(calculatorBotInfo);
        }
    }
}
