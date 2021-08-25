package saversandloaders.database.converters.botinfoconverters;

import models.bots.*;
import saversandloaders.database.models.dbbotinfomodels.DBBotInfo;

import java.util.ArrayList;

public class BotInfoConverter {

    public static DBBotInfo convertBotInfoToDBBotInfo(BotInfo botInfo, BotInfoTypes botInfoType){
        DBBotInfo dbBotInfo = new DBBotInfo();

        switch (botInfoType){
            case GAMEBOT_INFO:
                dbBotInfo.setId(1);
                break;
            case QUESTIONBOT_INFO:
                dbBotInfo.setId(2);
                break;
            case CALCULATORBOT_INFO:
                dbBotInfo.setId(3);
                break;
        }

        dbBotInfo.setFollowersId(new ArrayList<>(botInfo.getFollowersId()));
        dbBotInfo.setUsername(botInfo.getUsername());

        return dbBotInfo;
    }

    public static GameBotInfo convertDBBotInfoToGameBotInfo(DBBotInfo dbBotInfo){
        return new GameBotInfo(new ArrayList<>(dbBotInfo.getFollowersId()));
    }

    public static QuestionBotInfo convertDBBotInfoToQuestionBotInfo(DBBotInfo dbBotInfo){
        return new QuestionBotInfo(new ArrayList<>(dbBotInfo.getFollowersId()));
    }

    public static CalculatorBotInfo convertDBBotInfoToCalculatorBotInfo(DBBotInfo dbBotInfo){
        return new CalculatorBotInfo(new ArrayList<>(dbBotInfo.getFollowersId()));
    }

}
