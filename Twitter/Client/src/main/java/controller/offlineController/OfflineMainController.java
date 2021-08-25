package controller.offlineController;

import controller.MainController;
import events.EventManager;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestChatInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestGroupInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserChatsInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.RequestUserGroupsEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.UpdateOfflineChatInfo;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.UpdateOfflineGroupInfo;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.settingpage.SettingPageEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.luser.messagingpage.SendChatInfoServerEvent;
import events.serverevents.luser.messagingpage.SendGroupInfoServerEvent;
import events.serverevents.luser.messagingpage.SendUserChatsInfoEvent;
import events.serverevents.luser.messagingpage.SendUserGroupsInfoEvent;
import events.serverevents.main.ServerEvent;
import models.messaging.Chat;
import models.messaging.Group;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class OfflineMainController implements LogicEventManager, EventManager {
    private final MainController mainController;
    private SendUserChatsInfoEvent savedChatsInfo;
    private SendUserGroupsInfoEvent savedGroupsInfo;
    private final ArrayList<Chat> savedChats;
    private final ArrayList<Group> savedGroups;
    private final ArrayList<SettingPageEvent> queuedEvents;

    public OfflineMainController(MainController mainController) {
        this.mainController = mainController;
        this.savedChats = new ArrayList<>();
        this.savedGroups = new ArrayList<>();
        this.queuedEvents = new ArrayList<>();
    }


    public void sendQueuedEvents(){
        for (SettingPageEvent settingPageEvent : queuedEvents){
            try {
                mainController.sendClientEvent(settingPageEvent, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveChatInfo(Chat chat){
        for (int i = 0; i < savedChats.size(); i++) {
            if (savedChats.get(i).getToken().equals(chat.getToken())){
                savedChats.remove(i);
                break;
            }
        }
        savedChats.add(chat);
    }

    private void saveGroupInfo(Group group){
        for (int i = 0; i < savedGroups.size(); i++) {
            if (savedGroups.get(i).getToken().equals(group.getToken())){
                savedGroups.remove(i);
                break;
            }
        }
        savedGroups.add(group);
    }

    private void loadChatInfo(String token){
        for (Chat chat : savedChats){
            if (chat.getToken().equals(token)){
                sendLogicalEvent(new SendChatInfoServerEvent(chat));
                return;
            }
        }

        JOptionPane.showMessageDialog(null,
                "This request can't be responsed in offline mode.");
    }

    private void loadGroupInfo(String token){
        for (Group group : savedGroups){
            if (group.getToken().equals(token)){
                sendLogicalEvent(new SendGroupInfoServerEvent(group));
                return;
            }
        }
        JOptionPane.showMessageDialog(null,
                "This request can't be responsed in offline mode.");
    }

    public void manageReConnection(){
        for (SettingPageEvent settingPageEvent : queuedEvents){
            try {
                Thread.sleep(100);
                System.out.println("settingPageEvent.getClass() = " + settingPageEvent.getClass());
                sendClientEvent(settingPageEvent, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveNeededInformations(ServerEvent serverEvent){
        if (serverEvent.getClass() == SendUserChatsInfoEvent.class){
            savedChatsInfo = ((SendUserChatsInfoEvent) serverEvent);
        }else
        if (serverEvent.getClass() == SendUserGroupsInfoEvent.class){
            savedGroupsInfo = ((SendUserGroupsInfoEvent) serverEvent);
        }
        if (serverEvent.getClass() == SendChatInfoServerEvent.class){
            saveChatInfo(((SendChatInfoServerEvent) serverEvent).getChat());
        } else
        if (serverEvent.getClass() == SendGroupInfoServerEvent.class){
            saveGroupInfo(((SendGroupInfoServerEvent) serverEvent).getGroup());
        }
    }

    public <E extends ClientEvent> void interpretClientEvent(E clientEvent) throws IOException {
        if (clientEvent.getClass() == RequestUserChatsInfoEvent.class){
            sendLogicalEvent(savedChatsInfo);
        }else
        if (clientEvent.getClass() == RequestUserGroupsEvent.class){
            sendLogicalEvent(savedGroupsInfo);
        }else
        if (clientEvent.getClass() == RequestChatInfoEvent.class){
            loadChatInfo(((RequestChatInfoEvent)clientEvent).getToken());
        }else
        if (clientEvent.getClass() == RequestGroupInfoEvent.class){
            loadGroupInfo(((RequestGroupInfoEvent)clientEvent).getToken());
        }else
        if (clientEvent.getClass() == UpdateOfflineChatInfo.class){
            this.saveChatInfo(((UpdateOfflineChatInfo) clientEvent).getChat());
        }else
        if(clientEvent.getClass() == UpdateOfflineGroupInfo.class){
            this.saveGroupInfo(((UpdateOfflineGroupInfo)clientEvent).getGroup());
        } else
        {
            try {
                queuedEvents.add((SettingPageEvent) clientEvent);
            }catch (ClassCastException e){
                JOptionPane.showMessageDialog(null,
                        "This request can't be responsed in offline mode.");
            }
        }
    }


    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) {
        try {
            mainController.receiveServerEvent(logicalEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpretClientEvent(clientEvent);
    }


    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        mainController.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {

    }
}
