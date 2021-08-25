package events.serverevents.luser.messagingpage;

import java.util.ArrayList;

public class SendUserChatsInfoEvent extends MessagingPageServerEvent {
    private final ArrayList<ArrayList<String>> userChatsInfo;

    public SendUserChatsInfoEvent(ArrayList<ArrayList<String>> userChatsInfo) {
        this.userChatsInfo = userChatsInfo;
    }

    public ArrayList<ArrayList<String>> getUserChatsInfo() {
        return userChatsInfo;
    }
}
