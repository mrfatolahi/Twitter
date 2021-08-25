package saversandloaders.database.converters.userconverter.messagingmodelsconverters;

import saversandloaders.database.models.dbmessagingmodels.DBGroup;
import saversandloaders.database.models.dbmessagingmodels.DBGroupMessage;
import models.messaging.ChatMassage;
import models.messaging.Group;

import java.util.ArrayList;

public class GroupConverter {
    public static Group convertDBGroupToGroup(DBGroup dbGroup){
        Group group = new Group();
//        group.setId(dbGroup.getId());
        group.setName(dbGroup.getName());
        group.setToken(dbGroup.getToken());
        group.setUsersIds(new ArrayList<>(dbGroup.getUsersIds()));
        group.setMembersUsernames(new ArrayList<>(dbGroup.getMembersUsernames()));

        ArrayList<ChatMassage> a = new ArrayList<>();
        for (DBGroupMessage dbGroupMessage : dbGroup.getGroupMessages()){
            a.add(GroupMessageConverter.ConvertDBGroupMessageToGroupMessage(dbGroupMessage));
        }
        group.setChatMassages(a);

        return group;
    }

    public static DBGroup convertGroupToDBGroup(Group group){
        DBGroup dbGroup = new DBGroup();

//        dbGroup.setId(dbGroup.getId());
        dbGroup.setName(group.getName());
        dbGroup.setToken(group.getToken());
        dbGroup.setUsersIds(group.getUsersIds());
        dbGroup.setMembersUsernames(group.getMembersUsernames());

        for (ChatMassage chatMassage : group.getChatMassages()){
            dbGroup.getGroupMessages().add(GroupMessageConverter.convertGroupMessageToDBGroupMessage(dbGroup, chatMassage));
        }

        return dbGroup;
    }
}
