package models.bots;

import events.serverevents.luser.explorerpage.UserInfoServerEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionBotInfo extends BotInfo{

    public QuestionBotInfo() {
        super("QuestionBot");
    }

    public QuestionBotInfo(ArrayList<Integer> followersId) {
        super("GameBot", followersId);
    }

    @Override
    public UserInfoServerEvent generateBotInfoAsEvent(){
        return new UserInfoServerEvent
                (
                        -20, followersId.size(), null, -1,
                        "Question:", "Bot", "QuestionBot",
                        "Question bot, it presents a simple quiz made from 5 questions.",
                        null, false, true
                );
    }
}
