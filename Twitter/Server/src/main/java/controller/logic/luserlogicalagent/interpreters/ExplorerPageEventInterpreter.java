package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage.ERequestUserInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage.ExplorerPageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.explorerpage.RequestHotTweetsEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.luser.explorerpage.SendHotTweetsServerEvent;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;
import events.serverevents.main.ServerEvent;
import models.user.User;
import saversandloaders.loaders.UserLoader;

import java.io.IOException;

public class ExplorerPageEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public ExplorerPageEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <K extends ExplorerPageEvent> void interpreteExplorerPageEvent(K explorerPageEvent) throws IOException {
        if (explorerPageEvent.getClass() == RequestHotTweetsEvent.class){
            this.sendLogicalEvent(new SendHotTweetsServerEvent(mainInterPreter.getlUserLogicalAgent().gethottweetsTweets()));
        } else
        if (explorerPageEvent.getClass() == ERequestUserInfoEvent.class){
            sendUserInfo(explorerPageEvent);
        }
    }

    private void sendUserInfo(ExplorerPageEvent explorerPageEvent) throws IOException {
        String username = ((ERequestUserInfoEvent) explorerPageEvent).getUsername();
        if (username.equals("GameBot")){
            sendLogicalEvent(
                     mainInterPreter.getlUserLogicalAgent().getLogicalAgent()
                    .getClientHandler().getMainController().getBotController()
                    .getGameBotsManager().getGameBotInfo().generateBotInfoAsEvent()
            );
            return;
        } else
        if (username.equals("QuestionBot")){
            sendLogicalEvent(
                    mainInterPreter.getlUserLogicalAgent().getLogicalAgent()
                            .getClientHandler().getMainController().getBotController()
                            .getQuestionBotManager().getQuestionBotInfo().generateBotInfoAsEvent()
            );
            return;
        } else
        if (username.equals("CalculatorBot")){
            sendLogicalEvent(
                    mainInterPreter.getlUserLogicalAgent().getLogicalAgent()
                            .getClientHandler().getMainController().getBotController()
                            .getCalculatorBotManager().getCalculatorBotInfo().generateBotInfoAsEvent()
            );
            return;
        }
        try {
            sendLogicalEvent(User.generateUserInfoEvent(UserLoader.loadUser(((ERequestUserInfoEvent) explorerPageEvent).getUsername())));
        } catch (Exception e){
            e.printStackTrace();
            sendLogicalEvent(new UserInfoServerEvent(-1, -1, null, -1, null, null, null, null, null, false, false));
        }
    }



    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteExplorerPageEvent((ExplorerPageEvent) clientEvent);
    }
}
