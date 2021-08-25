package controller.botmanager;

import XLogger.MLogger;
import config.Config;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import models.bots.QuestionBotInfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class QuestionBotManager extends BotManager{
    private final QuestionBotInfo questionBotInfo;
    private int currentGroupMembers;
    private ArrayList<ArrayList<String>> receivedAnswers;


    public QuestionBotManager(BotController botController, QuestionBotInfo questionBotInfo) {
        super(botController);
        this.questionBotInfo = questionBotInfo;
        this.receivedAnswers = new ArrayList<>();
        start();
    }

    public String startNewQuiz(ArrayList<String> playersUsernames) throws Exception {
        this.busy = true;
        this.currentGroupMembers = playersUsernames.size();
        start();
//        System.out.println("botClass = " + botClass);
        botClass.getMethod("initialize", new Class[]{ArrayList.class}).invoke(null, playersUsernames);
        return ((String) botClass.getMethod("getFirstQuestion", new Class[]{}).invoke(null));
    }

    public String receiveOneAnswer(String username, int answerNumber) throws Exception {
        ArrayList<String> answer = new ArrayList<>();
        answer.add(username);
        answer.add(String.valueOf(answerNumber));
        receivedAnswers.add(answer);

        if (receivedAnswers.size() == currentGroupMembers){
            System.out.println("receivedAnswers = " + receivedAnswers);
            String output = ((String) botClass.getMethod("receiveAnswers", new Class[]{ArrayList.class}).invoke(null, receivedAnswers));
            receivedAnswers.clear();
            if ((boolean)botClass.getMethod("isGameFinished", new Class[]{}).invoke(null)){
                busy = false;
            }
            return output;
        }

        return "";
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

    public QuestionBotInfo getQuestionBotInfo() {
        return questionBotInfo;
    }

    @Override
    protected void checkConfigFile(){
        while (true){
            try {
                Thread.sleep(10000);
                String address = Config.getConfig("BotsURLs").getProperty("questionBot");
                if (receivedURLs.contains(address)) {
                    continue;
                }
                receivedURLs.add(address);
                URLClassLoader urlClassLoader;
                urlClassLoader = new URLClassLoader(new URL[]{new URL(address)});

                botClass = urlClassLoader.loadClass("Bot");
                System.out.println("Bot Loaded");
                MLogger.log("INFO: Question Loaded");
                busy = false;
            } catch (Exception e){
                busy = true;
                MLogger.log("WARN: Can't load QuestionBot class");
            }
        }
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {

    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {

    }
}
