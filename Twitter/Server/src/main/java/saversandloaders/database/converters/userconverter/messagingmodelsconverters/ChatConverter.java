package saversandloaders.database.converters.userconverter.messagingmodelsconverters;

import saversandloaders.database.models.dbmessagingmodels.DBChat;
import saversandloaders.database.models.dbmessagingmodels.DBChatMessage;
import models.messaging.Chat;
import models.messaging.ChatMassage;

import java.util.ArrayList;

public class ChatConverter {
    public static Chat convertDBChatToChat(DBChat dbChat){
        Chat chat = new Chat();
//        chat.setId(dbChat.getId());
        chat.setToken(dbChat.getToken());
        chat.setUser1Id(dbChat.getUser1Id());
        chat.setUser2Id(dbChat.getUser2Id());
        chat.setUser1Username(dbChat.getUser1Username());
        chat.setUser2Username(dbChat.getUser2Username());
        chat.setUser1NewMassages(dbChat.getUser1NewMassages());
        chat.setUser2NewMassages(dbChat.getUser2NewMassages());

        ArrayList<ChatMassage> a = new ArrayList<>();
        for (DBChatMessage dbChatMessage : dbChat.getChatMassages()){
            a.add(ChatMessageConverter.ConvertDBChatMessageToChatMessage(dbChatMessage));
        }
        chat.setChatMassages(a);

        return chat;
    }

    public static DBChat convertChatToDBChat(Chat chat){
        DBChat dbChat = new DBChat();

//        dbChat.setId(chat.getId());
        dbChat.setToken(chat.getToken());
        dbChat.setUser1Id(chat.getUser1Id());
        dbChat.setUser2Id(chat.getUser2Id());
        dbChat.setUser1Username(chat.getUser1Username());
        dbChat.setUser2Username(chat.getUser2Username());
        dbChat.setUser1NewMassages(chat.getUser1NewMassages());
        dbChat.setUser2NewMassages(chat.getUser2NewMassages());

        for (ChatMassage chatMassage : chat.getChatMassages()){
            dbChat.getChatMassages().add(ChatMessageConverter.convertChatMessageToDBChatMessage(chatMassage));
        }

        return dbChat;
    }
}
