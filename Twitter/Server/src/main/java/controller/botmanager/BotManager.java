package controller.botmanager;

import controller.MainController;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.bots.BotEventCE;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public abstract class BotManager implements LogicEventManager {
    protected final BotController botController;
    protected final ArrayList<String> receivedURLs;
    protected Class botClass;
    protected boolean busy;

    public BotManager(BotController botController) {
        this.botController = botController;
        this.receivedURLs = new ArrayList<>();
    }

    public void start(){}

    public void addFollower(int id) throws IOException {}


    protected void checkConfigFile() throws ClassNotFoundException, MalformedURLException, InterruptedException {}

    protected <E extends BotEventCE>void interpreteReceivedEvent(E event){}

    public String receiveNewCommand(String command, int commanderId) throws Exception {
        return null;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
