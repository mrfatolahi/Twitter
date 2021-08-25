package saversandloaders.database.converters.userconverter.othermodelsconverters;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbothermodels.DBFollowRequest;
import models.other.FollowRequest;

public class FollowRequestConverter {
    public static FollowRequest convertDBFollowRequestToFollowRequest(DBFollowRequest dbFollowRequest){
        FollowRequest followRequest = new FollowRequest();
        followRequest.setSenderId(dbFollowRequest.getSenderId());
        followRequest.setReceiverId(dbFollowRequest.getReceiverId());
        followRequest.setReceiverUsername(dbFollowRequest.getReceiverUsername());
        followRequest.setText(dbFollowRequest.getText());
        followRequest.setSendtime(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbFollowRequest.getSendtime()));
        followRequest.setAccepted(dbFollowRequest.isAccepted());
        return followRequest;
    }

    public static DBFollowRequest convertFollowRequestToDBFollowRequest(FollowRequest followRequest){
        DBFollowRequest dbFollowRequest = new DBFollowRequest();
        dbFollowRequest.setSenderId(followRequest.getSenderId());
        dbFollowRequest.setReceiverId(followRequest.getReceiverId());
        dbFollowRequest.setReceiverUsername(followRequest.getReceiverUsername());
        dbFollowRequest.setText(followRequest.getText());
        dbFollowRequest.setSendtime(MDateTimeConverter.convertMDateTimeToDBMDateTime(followRequest.getSendtime()));
        dbFollowRequest.setAccepted(followRequest.isAccepted());
        return dbFollowRequest;
    }
}
