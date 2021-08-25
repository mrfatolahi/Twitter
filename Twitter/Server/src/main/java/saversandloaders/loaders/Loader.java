package saversandloaders.loaders;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import models.bots.CalculatorBotInfo;
import models.bots.GameBotInfo;
import models.bots.QuestionBotInfo;
import saversandloaders.database.dbloaders.DBLoader;
import saversandloaders.database.models.dbbotinfomodels.DBBotInfo;
import saversandloaders.json.jsonloaders.JLoader;

import java.io.File;
import java.io.IOException;

public class Loader {
    public static GameBotInfo loadGameBotInfo() throws IOException {
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JLoader.jLoadGameBotInfo();
        } else {
            GameBotInfo gameBotInfo = DBLoader.loadGameBotInfo();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(gameBotInfo));
            return gameBotInfo;
        }
    }

    public static QuestionBotInfo loadQuestionBotInfo() throws IOException {
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JLoader.jLoadquestionBotInfo();
        } else {
            return DBLoader.loadQuestionBotInfo();
        }
    }

    public static CalculatorBotInfo loadCalculatorBotInfo() throws IOException {
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JLoader.jLoadcalculatorBotInfo();
        } else {
            return DBLoader.loadCalculatorBotInfo();
        }
    }
}
