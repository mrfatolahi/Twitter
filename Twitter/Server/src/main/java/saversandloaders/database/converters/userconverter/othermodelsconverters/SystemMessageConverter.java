package saversandloaders.database.converters.userconverter.othermodelsconverters;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbothermodels.DBSystemMessage;
import models.other.SystemMassage;

public class SystemMessageConverter {
    public static SystemMassage convertDBSystemMessageToSystemMessage(DBSystemMessage dbSystemMessage){
        SystemMassage systemMassage = new SystemMassage();

        systemMassage.setSenderId(dbSystemMessage.getSenderId());
        systemMassage.setReceiverId(dbSystemMessage.getReceiverId());
        systemMassage.setText(dbSystemMessage.getText());

        systemMassage.setSendtime(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbSystemMessage.getSendtime()));
        return systemMassage;
    }

    public static DBSystemMessage convertSystemMessageToDBSystemMessage(SystemMassage systemMassage){
        DBSystemMessage dbSystemMessage = new DBSystemMessage();

        dbSystemMessage.setSenderId(systemMassage.getSenderId());
        dbSystemMessage.setReceiverId(systemMassage.getReceiverId());
        dbSystemMessage.setText(systemMassage.getText());

        dbSystemMessage.setSendtime(MDateTimeConverter.convertMDateTimeToDBMDateTime(systemMassage.getSendtime()));
        return dbSystemMessage;
    }
}
