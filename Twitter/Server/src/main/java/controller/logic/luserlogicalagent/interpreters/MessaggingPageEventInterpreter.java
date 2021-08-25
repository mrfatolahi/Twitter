package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.*;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.luser.messagingpage.*;
import events.serverevents.main.ServerEvent;
import models.messaging.Chat;
import models.messaging.Group;
import models.user.User;
import saversandloaders.loaders.UserLoader;
import saversandloaders.savers.UserSaver;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MessaggingPageEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public MessaggingPageEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <J extends MessagingPageEvent> void interpreteMessagingPageEvent(J messagingPageEvent) throws IOException {
        if (messagingPageEvent.getClass() == RequestMakingNewChatEvent.class){
            if (((RequestMakingNewChatEvent) messagingPageEvent).getUserToChatUsername().equals("GameBot") ||
                    ((RequestMakingNewChatEvent) messagingPageEvent).getUserToChatUsername().equals("QuestionBot") ||
                    ((RequestMakingNewChatEvent) messagingPageEvent).getUserToChatUsername().equals("CalculatorBot")){
                startChatWithBot(((RequestMakingNewChatEvent) messagingPageEvent));
                return;
            }
            int userToChatId = UserLoader.loadIdWithUsername(((RequestMakingNewChatEvent) messagingPageEvent).getUserToChatUsername());
            this.sendLogicalEvent(new DeclareNewChatMadeEvent(mainInterPreter.getlUserLogicalAgent().startChatWith(userToChatId)));
        } else
        if (messagingPageEvent.getClass() == RequestUserChatsInfoEvent.class){
            sendUserChatsInfo();
        } else
        if (messagingPageEvent.getClass() == CreateNewGroupEvent.class){
            mainInterPreter.getlUserLogicalAgent().createNewGroup(((CreateNewGroupEvent) messagingPageEvent).getGroupName(), ((CreateNewGroupEvent) messagingPageEvent).getMembersUsernames());
            this.sendLogicalEvent(new DeclareNewGroupMadeEvent());
        } else
        if (messagingPageEvent.getClass() == RequestUserGroupsEvent.class){
            sendUserGroupsInfo();
        } else
        if (messagingPageEvent.getClass() == SpreadEvent.class){
            mainInterPreter.getlUserLogicalAgent().spread(((SpreadEvent) messagingPageEvent).getText(), ((SpreadEvent) messagingPageEvent).getMembersUsernames());
        } else
        if (messagingPageEvent.getClass() == RequestUserSavedMessagesEvent.class){
            System.out.println(mainInterPreter.getlUserLogicalAgent().getUser().getSavedmassages());
            sendLogicalEvent(new SendUserSavedMessagesEvent(mainInterPreter.getlUserLogicalAgent().getUser().getSavedmassages()));
        } else
        if (messagingPageEvent.getClass() == RequestGroupInfoEvent.class){
            sendGroupInfo(((RequestGroupInfoEvent)messagingPageEvent).getToken());
        } else
        if (messagingPageEvent.getClass() == RequestChatInfoEvent.class){
            sendChatInfo(((RequestChatInfoEvent) messagingPageEvent).getToken());
        } else
        if (messagingPageEvent.getClass() == RequestUserChatsWithBotsInfoEvent.class){
            sendUserChatsWithBotsInfo();
        } else
        if (messagingPageEvent.getClass() == AddNewMemberToGroupWithHyperLink.class){
            addNewMemberToGroupFromHyperLink(((AddNewMemberToGroupWithHyperLink) messagingPageEvent));
        }
    }

    private void startChatWithBot(RequestMakingNewChatEvent requestMakingNewChatEvent) throws IOException {
        if (requestMakingNewChatEvent.getUserToChatUsername().equals("GameBot")){
            Chat chat = new Chat
                    (
                    mainInterPreter.getlUserLogicalAgent().getUser().getId(),
                    -10, mainInterPreter.getlUserLogicalAgent().getUser().getUsername().getText(),
                    "GameBot"
                    );
            mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots().add(chat);
            UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());
            this.sendLogicalEvent(new DeclareNewChatMadeEvent(chat));
        } else
        if (requestMakingNewChatEvent.getUserToChatUsername().equals("QuestionBot")){
            Chat chat = new Chat
                    (
                            mainInterPreter.getlUserLogicalAgent().getUser().getId(),
                            -20, mainInterPreter.getlUserLogicalAgent().getUser().getUsername().getText(),
                            "QuestionBot"
                    );
            mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots().add(chat);
            UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());
            this.sendLogicalEvent(new DeclareNewChatMadeEvent(chat));
        } else
        if (requestMakingNewChatEvent.getUserToChatUsername().equals("CalculatorBot")){
            Chat chat = new Chat
                    (
                            mainInterPreter.getlUserLogicalAgent().getUser().getId(),
                            -30, mainInterPreter.getlUserLogicalAgent().getUser().getUsername().getText(),
                            "CalculatorBot"
                    );
            mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots().add(chat);
            UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());
            this.sendLogicalEvent(new DeclareNewChatMadeEvent(chat));
        }
    }

    private void sendUserChatsWithBotsInfo() throws IOException {
        ArrayList<ArrayList<String>> chatsInfo = new ArrayList<>();
        for (Chat chat : mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots()){
            ArrayList<String> chatInfo = new ArrayList<>();
            chatInfo.add(chat.getToken());
            chatInfo.add(chat.getUser1Username());
            chatInfo.add(chat.getUser2Username());
            chatInfo.add(String.valueOf(chat.getUser1Id()));
            chatsInfo.add(chatInfo);
        }
        sendLogicalEvent(new SendUserChatsWithBotsInfoEvent(chatsInfo));
    }

    private void sendUserChatsInfo(){
        ArrayList<ArrayList<String>> chatsInfo = new ArrayList<>();
        for (Chat chat : mainInterPreter.getlUserLogicalAgent().getUser().getChats()){
            ArrayList<String> chatInfo = new ArrayList<>();
            chatInfo.add(chat.getToken());
            chatInfo.add(chat.getUser1Username());
            chatInfo.add(chat.getUser2Username());
            chatInfo.add(String.valueOf(chat.getUser1Id()));
            chatsInfo.add(chatInfo);
        }
        try {
            sendLogicalEvent(new SendUserChatsInfoEvent(chatsInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendUserGroupsInfo(){
        ArrayList<ArrayList<String>> groupsInfo = new ArrayList<>();
        for (Group group : mainInterPreter.getlUserLogicalAgent().getUser().getGroups()){
            ArrayList<String> groupInfo = new ArrayList<>();
            groupInfo.add(group.getToken());
            groupInfo.add(group.getName());
            groupsInfo.add(groupInfo);
        }
        try {
            sendLogicalEvent(new SendUserGroupsInfoEvent(groupsInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendChatInfo(String token){
        for (Chat chat : mainInterPreter.getlUserLogicalAgent().getUser().getChats()){
            if (chat.getToken().equals(token)){
                try {
                    sendLogicalEvent(new SendChatInfoServerEvent(chat));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (Chat chat : mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots()){
            if (chat.getToken().equals(token)){
                try {
                    sendLogicalEvent(new SendChatInfoServerEvent(chat));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendGroupInfo(String token){
        for (Group group : mainInterPreter.getlUserLogicalAgent().getUser().getGroups()){
            if (group.getToken().equals(token)){
                try {
                    sendLogicalEvent(new SendGroupInfoServerEvent(group));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addNewMemberToGroupFromHyperLink(AddNewMemberToGroupWithHyperLink addNewMemberToGroupWithHyperLink) throws IOException {

        for (Group group : UserLoader.loadUser(addNewMemberToGroupWithHyperLink.getGroupOwnerId()).getGroups()){
            if (group.getToken().equals(addNewMemberToGroupWithHyperLink.getGroupToken())){
                Group group1 = new Group(group.getName());
                group1.setId(group.getId());
                group1.setToken(group.getToken());
                group1.setUsersIds(group.getUsersIds());
                group1.setMembersUsernames(group.getMembersUsernames());
                group1.setChatMassages(group.getChatMassages());
                group1.addMember(mainInterPreter.getlUserLogicalAgent().getUser().getId(), mainInterPreter.getlUserLogicalAgent().getUser().getUsername().getText());

                Q:for (int memberId : group1.getUsersIds()){
                    User member = UserLoader.loadUser(memberId);
                    for (Group memberGroup : member.getGroups()){
                        if (memberGroup.getToken().equals(group1.getToken())){
                            member.getGroups().remove(memberGroup);
                            member.getGroups().add(group1);
                            UserSaver.saveUser(member);
                            try {
                                mainInterPreter.getClientById(member.getId()).updateLoggedInUserInfoFromFile();
                            }catch (NullPointerException ignored){}
                            continue  Q;
                        }
                    }

                    member.getGroups().add(group1);
                    UserSaver.saveUser(member);
                    try {
                        mainInterPreter.getClientById(member.getId()).updateLoggedInUserInfoFromFile();
                    }catch (NullPointerException ignored){}

                }
                break;
            }
        }
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteMessagingPageEvent((MessagingPageEvent) clientEvent);
    }
}
