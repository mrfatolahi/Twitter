package controller;

import models.bots.CalculatorBotInfo;
import models.bots.GameBotGameInfo;
import models.bots.GameBotInfo;
import models.bots.QuestionBotInfo;
import models.messaging.Chat;
import models.messaging.ChatMassage;

import java.util.ArrayList;

public class IDGenerator {
    private final static ArrayList<Integer> ids = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> chatObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> chatMassageObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();
//    private final static ArrayList<Integer> botInfoObjectsIds = new ArrayList<>();



    public static int generateAnId(Object object){
//        int id = -1;

//        if (object.getClass() == GameBotInfo.class || object.getClass() == QuestionBotInfo.class || object.getClass() == CalculatorBotInfo.class || object.getClass() == GameBotGameInfo.class){
//            id = botInfoObjectsIds.size();
//            botInfoObjectsIds.add(id);
//        } else
//        if (object.getClass() == Chat.class){
//            id = chatObjectsIds.size();
//            chatObjectsIds.add(id);
//        } else
//        if (object.getClass() == ChatMassage.class){
//            id = chatMassageObjectsIds.size();
//            chatMassageObjectsIds.add(id);
//        }

//        return id;

        int id = ids.size();
        ids.add(id);
        return id;
    }
}
