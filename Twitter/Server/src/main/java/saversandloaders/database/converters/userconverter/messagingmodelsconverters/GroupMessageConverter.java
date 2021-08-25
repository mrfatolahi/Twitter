package saversandloaders.database.converters.userconverter.messagingmodelsconverters;

import models.other.hyperlink.HyperLink;
import models.other.hyperlink.HyperLinkType;
import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbmessagingmodels.DBGroup;
import saversandloaders.database.models.dbmessagingmodels.DBGroupMessage;
import models.messaging.ChatMassage;
import models.messaging.ChatMessageStatus;

public class GroupMessageConverter {
    public static DBGroupMessage convertGroupMessageToDBGroupMessage(DBGroup dbGroup, ChatMassage chatMassage){
        DBGroupMessage dbGroupMessage = new DBGroupMessage();

//        dbGroupMessage.setId(chatMassage.getId());
        dbGroupMessage.setUserId(chatMassage.getUserId());
        switch (chatMassage.getChatMessageStatus()){
            case HAVE_NOT_RECORDED_IN_SERVER:
                dbGroupMessage.setChatMessageStatus(1);
                break;
            case RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET:
                dbGroupMessage.setChatMessageStatus(2);
                break;
            case RECEIVER_HAVE_NOT_SEEN_THE_MESSAGE:
                dbGroupMessage.setChatMessageStatus(3);
                break;
            case RECEIVER_HAVE_RECEIVED_THE_MESSAGE:
                dbGroupMessage.setChatMessageStatus(4);
                break;
        }

        dbGroupMessage.setTimeCreated(MDateTimeConverter.convertMDateTimeToDBMDateTime(chatMassage.getTimeCreated()));
        dbGroupMessage.setText(chatMassage.getText());
        dbGroupMessage.setImagePath(chatMassage.getImagePath());

        if (chatMassage.getHyperlink() == null){
            dbGroupMessage.setHyperLinkText(null);
            dbGroupMessage.setHyperLinkToken(null);
            dbGroupMessage.setHyperLinkType(null);
        } else {
            dbGroupMessage.setHyperLinkText(chatMassage.getHyperlink().getText());
            dbGroupMessage.setHyperLinkToken(chatMassage.getHyperlink().getToken());

            switch (chatMassage.getHyperlink().getType()){
                case TWEET_LINK:
                    dbGroupMessage.setHyperLinkType("TWEET_LINK");
                    break;
                case BOT_CHAT_LINK:
                    dbGroupMessage.setHyperLinkType("BOT_CHAT_LINK");
                    break;
                case GROUP_LINK:
                    dbGroupMessage.setHyperLinkType("GROUP_LINK");
                    break;
                case PERSON_CHAT_LINK:
                    dbGroupMessage.setHyperLinkType("PERSON_CHAT_LINK");
                    break;
                case GROUP_INVITE_LINK:
                    dbGroupMessage.setHyperLinkType("GROUP_INVITE_LINK");
                    break;
            }
        }

        return dbGroupMessage;
    }

    public static ChatMassage ConvertDBGroupMessageToGroupMessage(DBGroupMessage dbGroupMessage){
        ChatMassage chatMassage = new ChatMassage();

//        chatMassage.setId(dbGroupMessage.getId());
        chatMassage.setUserId(dbGroupMessage.getUserId());
        chatMassage.setTimeCreated(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbGroupMessage.getTimeCreated()));
        chatMassage.setText(dbGroupMessage.getText());
        chatMassage.setImagePath(chatMassage.getImagePath());

        switch (dbGroupMessage.getChatMessageStatus()){
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

        if (dbGroupMessage.getHyperLinkType() != null){
            switch (dbGroupMessage.getHyperLinkType()){
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

        if (dbGroupMessage.getHyperLinkType() != null){
            chatMassage.setHyperlink(new HyperLink(dbGroupMessage.getHyperLinkText(), dbGroupMessage.getHyperLinkToken(), hyperLinkType));
        } else {
            chatMassage.setHyperlink(null);
        }

        return chatMassage;
    }
}
