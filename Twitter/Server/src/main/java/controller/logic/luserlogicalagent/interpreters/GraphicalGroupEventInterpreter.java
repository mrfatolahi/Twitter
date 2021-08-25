package controller.logic.luserlogicalagent.interpreters;

import controller.botmanager.QuestionBotManager;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalgroup.*;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.luser.messagingpage.graphicalgroup.GraphicalGroupDoubleBackEvent;
import events.serverevents.luser.messagingpage.graphicalgroup.SendGroupMessagesEvent;
import events.serverevents.main.ServerEvent;
import models.messaging.ChatMassage;
import models.messaging.ChatMessageStatus;
import models.messaging.Group;
import models.other.hyperlink.HyperLink;
import models.time.MDateTime;
import models.user.User;
import saversandloaders.loaders.UserLoader;
import saversandloaders.savers.UserSaver;

import java.io.IOException;
import java.util.ArrayList;

public class GraphicalGroupEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public GraphicalGroupEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <B extends GraphicalGroupEvent> void interpreteGraphicalGroupEvent(B graphicalGroupEvent) throws Exception {
        if (graphicalGroupEvent.getClass() == NewGroupMessageEvent.class){
            handleNewGroupMessageEvent((NewGroupMessageEvent) graphicalGroupEvent);
        } else
        if (graphicalGroupEvent.getClass() == AddMemberToGroupEvent.class){
            handleAddMemberToGroupEvent((AddMemberToGroupEvent) graphicalGroupEvent);
        } else
        if (graphicalGroupEvent.getClass() == RequestNewQuizEvent.class){
            createNewQuiz(((RequestNewQuizEvent) graphicalGroupEvent));
        } else
        if (graphicalGroupEvent.getClass() == LeaveGroupEvent.class){
            leaveGroup(((LeaveGroupEvent) graphicalGroupEvent).getGroup());
        } else
        if (graphicalGroupEvent.getClass() == NewScheduledGroupMessageEvent.class){
            handleScheduledMessage(((NewScheduledGroupMessageEvent) graphicalGroupEvent));
        }
    }

    private void handleNewGroupMessageEvent(NewGroupMessageEvent newGroupMessageEvent) throws Exception {
            for (Group group : mainInterPreter.getlUserLogicalAgent().getUser().getGroups()) {
                if (group.getUsersIds().containsAll((newGroupMessageEvent).getReceiversId()) && (newGroupMessageEvent).getReceiversId().size() == group.getUsersIds().size()) {
                    HyperLink hyperLink = null;
                    if (newGroupMessageEvent.getHyperLinkType() != null){
                        hyperLink = mainInterPreter.getlUserLogicalAgent().generateHyperLink(newGroupMessageEvent.getHyperLinkText(), newGroupMessageEvent.getHyperLinkDestination(), newGroupMessageEvent.getHyperLinkType());
                    }
                    group.sendMassage(mainInterPreter.getlUserLogicalAgent().getUser().getId(), (newGroupMessageEvent).getText(), (newGroupMessageEvent).getImagePath(), hyperLink);
                    Q:for (int memberId : (newGroupMessageEvent).getReceiversId()) {
                        User member = UserLoader.loadUser(memberId);
                        for (Group memberGroup : member.getGroups()) {
                            if (memberGroup.getUsersIds().containsAll(group.getUsersIds()) && (memberGroup.getUsersIds().size() == group.getUsersIds().size())) {
                                member.getGroups().remove(memberGroup);
                                member.getGroups().add(group);
                                UserSaver.saveUser(member);
                                try {
                                    mainInterPreter.getClientById(member.getId()).updateLoggedInUserInfoFromFile();
                                }catch (Exception ignored){}
                                continue Q;
                            }
                        }
                    }
                    ArrayList<ChatMassage> chatMassages1 = new ArrayList<>();
                    for (ChatMassage chatMassage : group.getChatMassages()) {
                        chatMassages1.add(new ChatMassage(chatMassage.getUserId(), chatMassage.getText(), chatMassage.getImagePath(), chatMassage.getHyperlink()));
                    }
                    for (ChatMassage chatMassage : chatMassages1) {
                        chatMassage.setChatMessageStatus(ChatMessageStatus.RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET);
                    }
                    for (int memberId : group.getUsersIds()) {
                        try {
                            mainInterPreter.getClientById(memberId).sendLogicalEvent(new SendGroupMessagesEvent(chatMassages1));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
            if (newGroupMessageEvent.getText().charAt(0) == '@'){
                handleNewQuestionBotCommand(newGroupMessageEvent);
            }
    }

    private void handleAddMemberToGroupEvent(AddMemberToGroupEvent addMemberToGroupEvent) throws IOException {
        ArrayList<Integer> membersIds = new ArrayList<>(addMemberToGroupEvent.getGroup().getUsersIds());
        for (Group group : mainInterPreter.getlUserLogicalAgent().getUser().getGroups()){
            if (group.getUsersIds().containsAll((addMemberToGroupEvent).getGroup().getUsersIds()) && group.getUsersIds().size() == (addMemberToGroupEvent).getGroup().getUsersIds().size()){
                ArrayList<String> d = new ArrayList<>(((addMemberToGroupEvent)).getNewMembersUsernames());
                ArrayList<Integer> f = new ArrayList<>((addMemberToGroupEvent).getGroup().getUsersIds());
                for (String newMemberUsername : d){
                    group.addMember(UserLoader.loadUser(String.valueOf(newMemberUsername)).getId(), newMemberUsername);
                }
                Q:for (int memberId : group.getUsersIds()){
                    User member = UserLoader.loadUser(memberId);
                    for (Group memberGroup : member.getGroups()){
                        if (memberGroup.getUsersIds().containsAll(f) && memberGroup.getUsersIds().size() == f.size()){
                            member.getGroups().remove(memberGroup);
                            member.getGroups().add(group);
                            UserSaver.saveUser(member);
                            try {
                                mainInterPreter.getClientById(member.getId()).updateLoggedInUserInfoFromFile();
                            }catch (NullPointerException ignored){}
                            continue  Q;
                        }
                    }

                    member.getGroups().add(group);
                    UserSaver.saveUser(member);
                    try {
                        mainInterPreter.getClientById(member.getId()).updateLoggedInUserInfoFromFile();
                    }catch (NullPointerException ignored){}

                }
                break;
            }
        }

        for (int memberId : membersIds){
            try {
                mainInterPreter.getClientById(memberId).sendLogicalEvent(new GraphicalGroupDoubleBackEvent());
            }catch (NullPointerException ignored){}
        }
    }

    private void createNewQuiz(RequestNewQuizEvent requestNewQuizEvent) throws Exception {
        QuestionBotManager questionBotManager = mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getBotController().getQuestionBotManager();

        ArrayList usernames = new ArrayList<String>();
        for (int memberId : requestNewQuizEvent.getGroupMembersIds()){
            usernames.add(UserLoader.loadUser(memberId).getUsername().getText());
        }
        String string = questionBotManager.startNewQuiz(usernames);


        NewGroupMessageEvent newGroupMessageEvent = new NewGroupMessageEvent(requestNewQuizEvent.getGroupMembersIds(), string, null);
        handleNewGroupMessageEvent(newGroupMessageEvent);
    }

    private void handleNewQuestionBotCommand(NewGroupMessageEvent newGroupMessageEvent) throws Exception{
        QuestionBotManager questionBotManager = mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getBotController().getQuestionBotManager();
        String output = questionBotManager.receiveOneAnswer(mainInterPreter.getlUserLogicalAgent().getUser().getUsername().getText(), Character.getNumericValue(newGroupMessageEvent.getText().charAt(1)));
        if (!output.equals("")){
            NewGroupMessageEvent newGroupMessageEvent1 = new NewGroupMessageEvent(newGroupMessageEvent.getReceiversId(), output, null);
            handleNewGroupMessageEvent(newGroupMessageEvent1);
        }
    }

    private void leaveGroup(Group group) throws IOException {
        String groupName = group.getName();
        for (int memberId : group.getUsersIds()){
            User member = UserLoader.loadUser(memberId);
            for (Group group1 : member.getGroups()) {
                if (group1.getUsersIds().containsAll(group.getUsersIds()) && group.getUsersIds().size() == group1.getUsersIds().size()) {
                    group1.removeMember(mainInterPreter.getlUserLogicalAgent().getUser().getId(), mainInterPreter.getlUserLogicalAgent().getUser().getUsername().getText());
                    UserSaver.saveUser(member);
                    try {
                        mainInterPreter.getClientById(memberId).updateLoggedInUserInfoFromFile();
                    }catch (Exception ignored){}
                }
            }
        }

        for (Group gr : mainInterPreter.getlUserLogicalAgent().getUser().getGroups()){
            if (gr.getName().equals(groupName)){
                mainInterPreter.getlUserLogicalAgent().getUser().getGroups().remove(gr);
                UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());
                try {
                    mainInterPreter.getClientById(mainInterPreter.getlUserLogicalAgent().getUser().getId()).updateLoggedInUserInfoFromFile();
                }catch (Exception ignored){}
                break;
            }
        }
    }

    private void handleScheduledMessage(NewScheduledGroupMessageEvent newScheduledGroupMessageEvent){
        int currentHour = MDateTime.now().getHour();
        int currentMinute = MDateTime.now().getMinute();

        int intervalPerMinute = ((newScheduledGroupMessageEvent.getHour() - currentHour)*60) + (newScheduledGroupMessageEvent.getMinute() - currentMinute);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                int intervalPerMinute1 = intervalPerMinute;
                NewGroupMessageEvent newGroupMessageEvent1 = new NewGroupMessageEvent(newScheduledGroupMessageEvent.getReceiversId(), newScheduledGroupMessageEvent.getText(), newScheduledGroupMessageEvent.getImagePath());
                while (true){
                    try {
                        Thread.sleep(60000);
                        intervalPerMinute1--;
                        if (intervalPerMinute1 == 0){
                            handleNewGroupMessageEvent(newGroupMessageEvent1);
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        })).start();
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteGraphicalGroupEvent((GraphicalGroupEvent) clientEvent);
    }
}
