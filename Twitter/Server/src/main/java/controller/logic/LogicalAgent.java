package controller.logic;

import XLogger.MLogger;
import controller.ClientHandler;
import controller.logic.luserlogicalagent.LUserLogicalAgent;
import controller.logic.nluserlogicalagent.NLUserLogicalAgent;
import events.clientevents.graphicalevents.loggeduser.LUserGraphicalEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.NewChatMassageEvent;
import events.clientevents.graphicalevents.notloggeduser.NLUserGraphicalEvent;
import events.clientevents.main.ClientEvent;
import events.clientevents.main.LogInWithIdEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;
import models.user.User;
import saversandloaders.loaders.UserLoader;

import java.io.IOException;


public class LogicalAgent implements LogicEventManager {
    private final ClientHandler clientHandler;
    private LUserLogicalAgent lUserLogicalAgent;
    private NLUserLogicalAgent nlUserLogicalAgent;


    public LogicalAgent(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.nlUserLogicalAgent = new NLUserLogicalAgent(this);
        this.lUserLogicalAgent = null;
    }


    public LUserLogicalAgent getlUserLogicalAgent() {
        return lUserLogicalAgent;
    }

    public void setlUserLogicalAgent(User user, boolean assignAuthToken) throws IOException {
        if (assignAuthToken){
            clientHandler.assignAuthToken();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.lUserLogicalAgent = new LUserLogicalAgent(user, this);
    }

    public NLUserLogicalAgent getNlUserLogicalAgent() {
        return nlUserLogicalAgent;
    }

    public void setNlUserLogicalAgent(NLUserLogicalAgent nlUserLogicalAgent) {
        this.nlUserLogicalAgent = nlUserLogicalAgent;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public ClientHandler getClientById(int id){
        return clientHandler.getClientById(id);
    }

    public void updateLoggedInUserInfoFromFile(){
        try {
            lUserLogicalAgent.updateInfFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        MLogger.log("Event: "+logicalEvent.getClass().toString());
        clientHandler.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        if (clientEvent.getClass() == NewChatMassageEvent.class){
            lUserLogicalAgent.receiveGraphicalEvent(clientEvent);
            MLogger.log("Event: "+ clientEvent.getClass().toString());
            return;
        }
        if (clientEvent.getClass() == LogInWithIdEvent.class){
            System.out.println("SAS");
            this.setlUserLogicalAgent(UserLoader.loadUser(((LogInWithIdEvent)clientEvent).getId()), false);
            this.getClientHandler().setId(((LogInWithIdEvent)clientEvent).getId());
            this.getClientHandler().setAuthToken(((LogInWithIdEvent) clientEvent).getAuthToken());
            return;
        }
        try {
            lUserLogicalAgent.receiveGraphicalEvent((LUserGraphicalEvent) clientEvent);
        }catch (ClassCastException e){
            nlUserLogicalAgent.receiveGraphicalEvent((NLUserGraphicalEvent) clientEvent);
        }
    }
}
