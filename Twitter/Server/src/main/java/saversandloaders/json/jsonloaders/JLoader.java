package saversandloaders.json.jsonloaders;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.bots.CalculatorBotInfo;
import models.bots.GameBotInfo;
import models.bots.QuestionBotInfo;

import java.io.File;
import java.io.IOException;

public class JLoader {
    public static GameBotInfo jLoadGameBotInfo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        GameBotInfo gameBotInfo = objectMapper.readValue(new File("Database\\Bots\\GameBotInfo.json"), GameBotInfo.class);
        return gameBotInfo;
    }

    public static QuestionBotInfo jLoadquestionBotInfo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        QuestionBotInfo questionBotInfo = objectMapper.readValue(new File("Database\\Bots\\QuestionBotInfo.json"), QuestionBotInfo.class);
        return questionBotInfo;
    }

    public static CalculatorBotInfo jLoadcalculatorBotInfo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        CalculatorBotInfo calculatorBotInfo = objectMapper.readValue(new File("Database\\Bots\\CalculatorBotInfo.json"), CalculatorBotInfo.class);
        return calculatorBotInfo;
    }
}
