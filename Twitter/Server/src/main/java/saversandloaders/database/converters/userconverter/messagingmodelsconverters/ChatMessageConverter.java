package saversandloaders.database.converters.userconverter.messagingmodelsconverters;

import models.other.hyperlink.HyperLink;
import models.other.hyperlink.HyperLinkType;
import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbmessagingmodels.DBChatMessage;
import models.messaging.ChatMassage;
import models.messaging.ChatMessageStatus;

public class ChatMessageConverter {
    public static DBChatMessage convertChatMessageToDBChatMessage(ChatMassage chatMassage){
        DBChatMessage dbChatMessage = new DBChatMessage();

//        dbChatMessage.setId(chatMassage.getId());

        dbChatMessage.setUserId(chatMassage.getUserId());
        switch (chatMassage.getChatMessageStatus()){
            case HAVE_NOT_RECORDED_IN_SERVER:
                dbChatMessage.setChatMessageStatus(1);
                break;
            case RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET:
                dbChatMessage.setChatMessageStatus(2);
                break;
            case RECEIVER_HAVE_NOT_SEEN_THE_MESSAGE:
                dbChatMessage.setChatMessageStatus(3);
                break;
            case RECEIVER_HAVE_RECEIVED_THE_MESSAGE:
                dbChatMessage.setChatMessageStatus(4);
                break;
        }

        dbChatMessage.setTimeCreated(MDateTimeConverter.convertMDateTimeToDBMDateTime(chatMassage.getTimeCreated()));
        dbChatMessage.setText(chatMassage.getText());
        dbChatMessage.setImagePath(chatMassage.getImagePath());

        if (chatMassage.getHyperlink() == null){
            dbChatMessage.setHyperLinkType(null);
            dbChatMessage.setHyperLinkText(null);
            dbChatMessage.setHyperLinkToken(null);
        } else {
            dbChatMessage.setHyperLinkText(chatMassage.getHyperlink().getText());
            dbChatMessage.setHyperLinkToken(chatMassage.getHyperlink().getToken());
            switch (chatMassage.getHyperlink().getType()){
                case TWEET_LINK:
                    dbChatMessage.setHyperLinkType("TWEET_LINK");
                    break;
                case BOT_CHAT_LINK:
                    dbChatMessage.setHyperLinkType("BOT_CHAT_LINK");
                    break;
                case GROUP_LINK:
                    dbChatMessage.setHyperLinkType("GROUP_LINK");
                    break;
                case PERSON_CHAT_LINK:
                    dbChatMessage.setHyperLinkType("PERSON_CHAT_LINK");
                    break;
                case GROUP_INVITE_LINK:
                    dbChatMessage.setHyperLinkType("GROUP_INVITE_LINK");
                    break;
            }
        }
        return dbChatMessage;
    }

    public static ChatMassage ConvertDBChatMessageToChatMessage(DBChatMessage dbChatMessage){
        ChatMassage chatMassage = new ChatMassage();

//        chatMassage.setId(dbChatMessage.getId());
        chatMassage.setUserId(dbChatMessage.getUserId());
        chatMassage.setTimeCreated(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbChatMessage.getTimeCreated()));
        chatMassage.setText(dbChatMessage.getText());
        chatMassage.setImagePath(chatMassage.getImagePath());

        switch (dbChatMessage.getChatMessageStatus()){
            case 1:
                chatMassage.setChatMessageStatus(ChatMessageStatus.HAVE_NOT_RECORDED_IN_SERVER);
                break;
            case 2:
                chatMassage.setChatMessageStatus(ChatMessageStatus.RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET);
                break;
            case 3:
                chatMassage.setChatMessageStatus(ChatMessageStatus.RECEIVER_HAVE_NOT_SEEN_THE_MESSAGE);
                break;
            case 4:
                chatMassage.setChatMessageStatus(ChatMessageStatus.RECEIVER_HAVE_RECEIVED_THE_MESSAGE);
                break;
        }

        HyperLinkType hyperLinkType = null;

        if (dbChatMessage.getHyperLinkType() != null){
            switch (dbChatMessage.getHyperLinkType()){
                case "TWEET_LINK":
                    hyperLinkType = HyperLinkType.TWEET_LINK;
                    break;
                case "BOT_CHAT_LINK":
                    hyperLinkType = HyperLinkType.BOT_CHAT_LINK;
                    break;
                case "GROUP_LINK":
                    hyperLinkType = HyperLinkType.GROUP_LINK;
                    break;
                case "PERSON_CHAT_LINK":
                    hyperLinkType = HyperLinkType.PERSON_CHAT_LINK;
                    break;
                case "GROUP_INVITE_LINK":
                    hyperLinkType = HyperLinkType.GROUP_INVITE_LINK;
                    break;
            }
        }

        if (dbChatMessage.getHyperLinkType() != null){
            chatMassage.setHyperlink(new HyperLink(dbChatMessage.getHyperLinkText(), dbChatMessage.getHyperLinkToken(), hyperLinkType));
        } else {
            chatMassage.setHyperlink(null);
        }

        return chatMassage;
    }
}
