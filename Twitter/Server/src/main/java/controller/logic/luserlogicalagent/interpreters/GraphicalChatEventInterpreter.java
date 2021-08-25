package controller.logic.luserlogicalagent.interpreters;

import controller.botmanager.CalculatorBotManager;
import controller.botmanager.GameBotsManager;
import events.serverevents.luser.messagingpage.graphicalchat.SendChatMessagesEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.GraphicalChatEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.graphicalchat.NewChatMassageEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;
import models.messaging.Chat;
import models.messaging.ChatMassage;
import models.messaging.ChatMessageStatus;
import models.messaging.Group;
import models.other.hyperlink.HyperLink;
import models.user.User;
import saversandloaders.loaders.UserLoader;
import saversandloaders.savers.UserSaver;

import java.io.IOException;
import java.util.ArrayList;

public class GraphicalChatEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public GraphicalChatEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <O extends GraphicalChatEvent> void interpreteGraphicalChatEvent(O graphicalChatEvent) throws Exception {
        if (graphicalChatEvent.getClass() == NewChatMassageEvent.class){
            if ((((NewChatMassageEvent) graphicalChatEvent)).getReceiverId() == -10 ||
                    (((NewChatMassageEvent) graphicalChatEvent)).getReceiverId() == -20||
                    (((NewChatMassageEvent) graphicalChatEvent)).getReceiverId() == -30){
                sendNewMessageToBot(((NewChatMassageEvent) graphicalChatEvent));
                return;
            }
            interpretNewChatMessageEvent((NewChatMassageEvent) graphicalChatEvent);
        }
    }

    private void interpretNewChatMessageEvent(NewChatMassageEvent newChatMassageEvent) throws IOException {
        for (Chat chat : mainInterPreter.getlUserLogicalAgent().getUser().getChats()){
            if (chat.getUser1Id() == (newChatMassageEvent).getReceiverId() || chat.getUser2Id() == (newChatMassageEvent).getReceiverId()){
                HyperLink hyperLink = null;
                if (newChatMassageEvent.getHyperLinkType() != null){
                    hyperLink = mainInterPreter.getlUserLogicalAgent().generateHyperLink(newChatMassageEvent.getHyperLinkText(), newChatMassageEvent.getHyperLinkDestination(), newChatMassageEvent.getHyperLinkType());
                }
                chat.sendMassage(mainInterPreter.getlUserLogicalAgent().getUser().getId(), (newChatMassageEvent).getText(), (newChatMassageEvent).getImagePath(), hyperLink);
                User receiver1 = UserLoader.loadUser((newChatMassageEvent).getReceiverId());
                for(int i = 0 ; i < receiver1.getChats().size() ; i++){
                    if(receiver1.getChats().get(i).getUser1Id() == chat.getUser1Id() && receiver1.getChats().get(i).getUser2Id() == chat.getUser2Id()){
                        ArrayList<Chat> ch = new ArrayList<>(receiver1.getChats());
                        ch.remove(i);
                        ch.add(chat);
                        receiver1.setChats(ch);
                        break;
                    }
                }
                UserSaver.saveUser(receiver1);
                UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());
                try {
                    mainInterPreter.getClientById(receiver1.getId()).updateLoggedInUserInfoFromFile();
                } catch (NullPointerException ignored){}

                ArrayList<ChatMassage> chatMassages1 = new ArrayList<>();
                for (ChatMassage chatMassage : chat.getChatMassages()){
                    chatMassages1.add(new ChatMassage(chatMassage.getUserId(), chatMassage.getText(), chatMassage.getImagePath(), chatMassage.getHyperlink()));
                }
                for (ChatMassage chatMassage : chatMassages1){
                    chatMassage.setChatMessageStatus(ChatMessageStatus.RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET);
                }

                try {
                    mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getClientById(chat.getUser1Id()).sendLogicalEvent(new SendChatMessagesEvent(chatMassages1));
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getClientById(chat.getUser2Id()).sendLogicalEvent(new SendChatMessagesEvent(chatMassages1));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendNewMessageToBot(NewChatMassageEvent newChatMassageEvent) throws Exception {
        if (newChatMassageEvent.getReceiverId() == -10){
            sendNewMessageToGameBot(newChatMassageEvent);
        } else
        if (newChatMassageEvent.getReceiverId() == -20){
            sendNewMessageToQuestionBot(newChatMassageEvent);
        } else
        if (newChatMassageEvent.getReceiverId() == -30){
            sendNewMessageToCalculatorBot(newChatMassageEvent);
        }
    }

    private void sendNewMessageToGameBot(NewChatMassageEvent newChatMassageEvent) throws Exception{
        Chat chat = null;
        for (Chat chat1 : mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots()){
            if (chat1.getUser2Id() == -10){
                chat = chat1;
                break;
            }
        }

        GameBotsManager gameBotsManager = mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getBotController().getGameBotsManager();

        chat.sendMassage(mainInterPreter.getlUserLogicalAgent().getUser().getId(), newChatMassageEvent.getText(), null, null);
        String botAnswer = "";
        if (newChatMassageEvent.getText().charAt(0) == '*'){
            botAnswer = "Ok";
        } else {
            botAnswer = gameBotsManager.receiveNewCommand(newChatMassageEvent.getText(), mainInterPreter.getlUserLogicalAgent().getUser().getId());
        }
        switch (botAnswer.charAt(0)){
            case '0':
                botAnswer = "Draw!";
                gameBotsManager.setCanCreateAGame(true);
                break;
            case '3':
                botAnswer = "Player 1 is winner!";
                gameBotsManager.setCanCreateAGame(true);
                break;
            case '4':
                botAnswer = "Player 1 is winner";
                gameBotsManager.setCanCreateAGame(true);
                break;
            default:
                botAnswer = botAnswer;
        }
        chat.sendMassage(-10, botAnswer, null, null);
        UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());

        ArrayList<ChatMassage> chatMassages1 = new ArrayList<>();
        for (ChatMassage chatMassage : chat.getChatMassages()){
            chatMassages1.add(new ChatMassage(chatMassage.getUserId(), chatMassage.getText(), chatMassage.getImagePath(), chatMassage.getHyperlink()));
        }
        mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getClientById(this.mainInterPreter.getlUserLogicalAgent().getUser().getId()).sendLogicalEvent(new SendChatMessagesEvent(chatMassages1));

        if ((!newChatMassageEvent.getText().contains("GENERATE_A_LINK")) && !(newChatMassageEvent.getText().charAt(0) == '*')){
            User opponent = UserLoader.loadUser(gameBotsManager.getOpponentId(mainInterPreter.getlUserLogicalAgent().getUser().getId()));
            Chat chat3 = null;
            for (Chat chat4 : opponent.getChatsWithBots()){
                if (chat4.getUser2Id() == -10){
                    chat3 = chat4;
                    break;
                }
            }

            chat3.sendMassage(-10, botAnswer, null, null);
            ArrayList<ChatMassage> chatMassages2 = new ArrayList<>();
            for (ChatMassage chatMassage : chat3.getChatMassages()){
                chatMassages2.add(new ChatMassage(chatMassage.getUserId(), chatMassage.getText(), chatMassage.getImagePath(), chatMassage.getHyperlink()));
            }
            UserSaver.saveUser(opponent);
            try {
                mainInterPreter.getClientById(opponent.getId()).updateLoggedInUserInfoFromFile();
            }catch (NullPointerException ignored){}
            mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientById(gameBotsManager.getOpponentId(mainInterPreter.getlUserLogicalAgent().getUser().getId())).updateLoggedInUserInfoFromFile();
            mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientById(gameBotsManager.getOpponentId(mainInterPreter.getlUserLogicalAgent().getUser().getId())).sendLogicalEvent(new SendChatMessagesEvent(chatMassages2));
        }
    }

    private void sendNewMessageToQuestionBot(NewChatMassageEvent newChatMassageEvent) throws Exception{
        Chat chat = null;
        for (Chat chat1 : mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots()){
            if (chat1.getUser2Id() == -20){
                chat = chat1;
                break;
            }
        }

        chat.sendMassage(mainInterPreter.getlUserLogicalAgent().getUser().getId(), newChatMassageEvent.getText(), null, null);
        UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());

        ArrayList<ChatMassage> chatMassages1 = new ArrayList<>();
        for (ChatMassage chatMassage : chat.getChatMassages()){
            chatMassages1.add(new ChatMassage(chatMassage.getUserId(), chatMassage.getText(), chatMassage.getImagePath(), chatMassage.getHyperlink()));
        }
        mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getClientById(this.mainInterPreter.getlUserLogicalAgent().getUser().getId()).sendLogicalEvent(new SendChatMessagesEvent(chatMassages1));
    }

    private void sendNewMessageToCalculatorBot(NewChatMassageEvent newChatMassageEvent) throws Exception{
        Chat chat = null;
        for (Chat chat1 : mainInterPreter.getlUserLogicalAgent().getUser().getChatsWithBots()){
            if (chat1.getUser2Id() == -30){
                chat = chat1;
                break;
            }
        }

        CalculatorBotManager calculatorBotManager = mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getBotController().getCalculatorBotManager();

        chat.sendMassage(mainInterPreter.getlUserLogicalAgent().getUser().getId(), newChatMassageEvent.getText(), null, null);
        String botAnswer = "";
        if (newChatMassageEvent.getText().charAt(0) == '*'){
            botAnswer = "Ok";
        }else {

            String text = newChatMassageEvent.getText();
            double firstNumber = 0;
            double secondNumber = 0;
            char opertation = '+';
            String a = "";
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    firstNumber = Double.parseDouble(a);
                    opertation = text.charAt(i + 1);
                    a = "";
                    for (int j = i + 3; j < text.length(); j++) {
                        a = a + text.charAt(j);
                    }
                    secondNumber = Double.parseDouble(a);
                    break;
                }
                a = a + text.charAt(i);
            }
            botAnswer = calculatorBotManager.calculate(firstNumber, secondNumber, opertation);
        }
        chat.sendMassage(-30, botAnswer, null, null);
        UserSaver.saveUser(mainInterPreter.getlUserLogicalAgent().getUser());

        ArrayList<ChatMassage> chatMassages1 = new ArrayList<>();
        for (ChatMassage chatMassage : chat.getChatMassages()){
            chatMassages1.add(new ChatMassage(chatMassage.getUserId(), chatMassage.getText(), chatMassage.getImagePath(), chatMassage.getHyperlink()));
        }
        mainInterPreter.getlUserLogicalAgent().getLogicalAgent().getClientHandler().getMainController().getClientById(this.mainInterPreter.getlUserLogicalAgent().getUser().getId()).sendLogicalEvent(new SendChatMessagesEvent(chatMassages1));
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpreteGraphicalChatEvent((GraphicalChatEvent) clientEvent);
    }
}
