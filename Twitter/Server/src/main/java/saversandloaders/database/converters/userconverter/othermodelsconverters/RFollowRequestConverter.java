package saversandloaders.database.converters.userconverter.othermodelsconverters;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbothermodels.DBRFollowRequest;
import models.other.FollowRequest;

public class RFollowRequestConverter {
    public static FollowRequest convertDBRFollowRequestToFollowRequest(DBRFollowRequest dbrFollowRequest){
        FollowRequest followRequest = new FollowRequest();
        followRequest.setSenderId(dbrFollowRequest.getSenderId());
        followRequest.setReceiverId(dbrFollowRequest.getReceiverId());
        followRequest.setReceiverUsername(dbrFollowRequest.getReceiverUsername());
        followRequest.setText(dbrFollowRequest.getText());
        followRequest.setSendtime(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbrFollowRequest.getSendtime()));
        followRequest.setAccepted(dbrFollowRequest.isAccepted());
        return followRequest;
    }

    public static DBRFollowRequest convertFollowRequestToDBRFollowRequest(FollowRequest followRequest){
        DBRFollowRequest dbrFollowRequest = new DBRFollowRequest();
        dbrFollowRequest.setSenderId(followRequest.getSenderId());
        dbrFollowRequest.setReceiverId(followRequest.getReceiverId());
        dbrFollowRequest.setReceiverUsername(followRequest.getReceiverUsername());
        dbrFollowRequest.setText(followRequest.getText());
        dbrFollowRequest.setSendtime(MDateTimeConverter.convertMDateTimeToDBMDateTime(followRequest.getSendtime()));
        dbrFollowRequest.setAccepted(followRequest.isAccepted());
        return dbrFollowRequest;
    }
}
