package models.bots;

import events.serverevents.luser.explorerpage.UserInfoServerEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class CalculatorBotInfo extends BotInfo{

    public CalculatorBotInfo() {
        super("CalculatorBot");
    }

    public CalculatorBotInfo(ArrayList<Integer> followersId) {
        super("GameBot", followersId);
    }

    @Override
    public UserInfoServerEvent generateBotInfoAsEvent(){
        return new UserInfoServerEvent
                (
                        -30, followersId.size(), null, -1,
                        "Calculator:", "Bot", "CalculatorBot",
                        "Calculator bot, a simple calculator that support simple opertations.",
                        null, false, true
                );
    }
}
