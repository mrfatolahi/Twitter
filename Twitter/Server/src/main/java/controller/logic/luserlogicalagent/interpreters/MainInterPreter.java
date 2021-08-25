package controller.logic.luserlogicalagent.interpreters;

import controller.ClientHandler;
import controller.logic.luserlogicalagent.LUserLogicalAgent;
import events.clientevents.graphicalevents.loggeduser.LUserGraphicalEvent;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicalprofile.GraphicalProfileEvent;
import events.clientevents.graphicalevents.loggeduser.graphicalmodels.graphicaltweet.GraphicalTweetEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage.ExplorerPageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.MessagingPageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.GraphicalChatEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.GraphicalGroupEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.PersonalPageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.SettingPageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.timelinepage.TimeLinePageEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;

import java.io.IOException;

public class MainInterPreter implements LogicEventManager {
    private final LUserLogicalAgent lUserLogicalAgent;
    private final GraphicalTweetEventInterpreter graphicalTweetEventInterpreter;
    private final GraphicalProfileEventInterpreter graphicalProfileEventInterpreter;
    private final EditProfilePageEventInterpreter editProfilePageEventInterpreter;
    private final PersonalPageEventInterpreter personalPageEventInterpreter;
    private final TimeLinePageEventInterpreter timeLinePageEventInterpreter;
    private final ExplorerPageEventInterpreter explorerPageEventInterpreter;
    private final MessaggingPageEventInterpreter messaggingPageEventInterpreter;
    private final SettingPageEventInterpreter settingPageEventInterpreter;
    private final GraphicalChatEventInterpreter graphicalChatEventInterpreter;
    private final GraphicalGroupEventInterpreter graphicalGroupEventInterpreter;

    public MainInterPreter(LUserLogicalAgent lUserLogicalAgent) {
        this.lUserLogicalAgent = lUserLogicalAgent;
        this.graphicalTweetEventInterpreter = new GraphicalTweetEventInterpreter(this);
        this.graphicalProfileEventInterpreter = new GraphicalProfileEventInterpreter(this);
        this.editProfilePageEventInterpreter = new EditProfilePageEventInterpreter(this);
        this.personalPageEventInterpreter = new PersonalPageEventInterpreter(this);
        this.timeLinePageEventInterpreter = new TimeLinePageEventInterpreter(this);
        this.explorerPageEventInterpreter = new ExplorerPageEventInterpreter(this);
        this.messaggingPageEventInterpreter = new MessaggingPageEventInterpreter(this);
        this.settingPageEventInterpreter = new SettingPageEventInterpreter(this);
        this.graphicalChatEventInterpreter = new GraphicalChatEventInterpreter(this);
        this.graphicalGroupEventInterpreter = new GraphicalGroupEventInterpreter(this);
    }

    public LUserLogicalAgent getlUserLogicalAgent() {
        return lUserLogicalAgent;
    }

    public EditProfilePageEventInterpreter getEditProfilePageEventInterpreter() {
        return editProfilePageEventInterpreter;
    }

    public  <S extends LUserGraphicalEvent> void interpret(S lUserGraphicalEvent) throws Exception {
        try {
            graphicalTweetEventInterpreter.receiveGraphicalEvent((GraphicalTweetEvent) lUserGraphicalEvent);
        } catch (ClassCastException | IOException e){
            try {
                graphicalProfileEventInterpreter.receiveGraphicalEvent((GraphicalProfileEvent) lUserGraphicalEvent);
            } catch (ClassCastException w){
                try {
                    personalPageEventInterpreter.receiveGraphicalEvent((PersonalPageEvent) lUserGraphicalEvent);
                } catch (ClassCastException | IOException t){
                    try {
                        timeLinePageEventInterpreter.receiveGraphicalEvent((TimeLinePageEvent) lUserGraphicalEvent);
                    } catch (ClassCastException c){
                        try {
                            explorerPageEventInterpreter.receiveGraphicalEvent((ExplorerPageEvent) lUserGraphicalEvent);
                        } catch (ClassCastException v){
                            try {
                                messaggingPageEventInterpreter.receiveGraphicalEvent((MessagingPageEvent) lUserGraphicalEvent);
                            } catch (ClassCastException k){
                                try {
                                    settingPageEventInterpreter.receiveGraphicalEvent((SettingPageEvent) lUserGraphicalEvent);
                                } catch (ClassCastException r){
                                    try {
                                        graphicalChatEventInterpreter.receiveGraphicalEvent(((GraphicalChatEvent) lUserGraphicalEvent));
                                    } catch (ClassCastException q){
                                        try {
                                            graphicalGroupEventInterpreter.receiveGraphicalEvent(((GraphicalGroupEvent) lUserGraphicalEvent));
                                        } catch (ClassCastException p){
                                            System.out.println();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ClientHandler getClientById(int id){
        return lUserLogicalAgent.getClientById(id);
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        lUserLogicalAgent.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpret((LUserGraphicalEvent) clientEvent);
    }
}
