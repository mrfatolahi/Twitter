package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.DeleteAccountEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.SettingPageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.ActiveAccountEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.DeActiveAccountEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.MakeAccountPrivateEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.privacysettingpage.MakeAccountPublicEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;
import saversandloaders.savers.UserSaver;

import java.io.IOException;

public class SettingPageEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public SettingPageEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <H extends SettingPageEvent> void interpreteSettingPageEvent(H settingPageEvent) throws IOException {
        if (settingPageEvent.getClass() == ActiveAccountEvent.class){
            mainInterPreter.getlUserLogicalAgent().activeAccount();
        } else
        if (settingPageEvent.getClass() == DeActiveAccountEvent.class){
            mainInterPreter.getlUserLogicalAgent().disableAccount();
        } else
        if (settingPageEvent.getClass() == MakeAccountPublicEvent.class){
            mainInterPreter.getlUserLogicalAgent().makeAccountPublic();
        } else
        if (settingPageEvent.getClass() == MakeAccountPrivateEvent.class){
            mainInterPreter.getlUserLogicalAgent().makeAccountPrivate();
        } else
        if (settingPageEvent.getClass() == DeleteAccountEvent.class){
            mainInterPreter.getlUserLogicalAgent().getUser().setDeleted(true);
            UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());
        }
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteSettingPageEvent((SettingPageEvent) clientEvent);
    }
}
