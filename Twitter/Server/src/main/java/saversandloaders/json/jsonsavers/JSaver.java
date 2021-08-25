package saversandloaders.json.jsonsavers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.bots.CalculatorBotInfo;
import models.bots.GameBotInfo;
import models.bots.QuestionBotInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSaver {
    public static void jSaveGameBotInfo(GameBotInfo gameBotInfo) throws IOException {
        File folder = new File("Database\\Bots\\GameBotInfo.json");
        if(!folder.exists()){folder.createNewFile();}

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        FileWriter fileWriter = new FileWriter(folder);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(fileWriter, gameBotInfo);
    }

    public static void jSaveQuestionBotInfo(QuestionBotInfo questionBotInfo) throws IOException {
        File folder = new File("Database\\Bots\\QuestionBotInfo.json");
        if(!folder.exists()){folder.createNewFile();}

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        FileWriter fileWriter = new FileWriter(folder);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(fileWriter, questionBotInfo);
    }

    public static void jSaveCalculatorBotInfo(CalculatorBotInfo calculatorBotInfo) throws IOException {
        File folder = new File("Database\\Bots\\CalculatorBotInfo.json");
        if(!folder.exists()){folder.createNewFile();}

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        FileWriter fileWriter = new FileWriter(folder);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(fileWriter, calculatorBotInfo);
    }
}
