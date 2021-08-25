package events.serverevents.luser.messagingpage;

import java.util.ArrayList;

public class SendUserChatsWithBotsInfoEvent extends MessagingPageServerEvent{
    private final ArrayList<ArrayList<String>> userChatsInfo;

    public SendUserChatsWithBotsInfoEvent(ArrayList<ArrayList<String>> userChatsInfo) {
        this.userChatsInfo = userChatsInfo;
    }

    public ArrayList<ArrayList<String>> getUserChatsInfo() {
        return userChatsInfo;
    }
}
