package saversandloaders.database.converters.userconverter.tweetconverter;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbtweetmodels.DBLike;
import models.tweet.Like;

public class LikeConverter {
    public static Like convertDBLikeToLike(DBLike dbLike){
        Like like = new Like();
        like.setUserId(dbLike.getUserId());
        like.setDateAndTimeCreated(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbLike.getDateAndTimeCreated()));
        return like;
    }

    public static DBLike convertLikeToDBLike(Like like){
        DBLike dbLike = new DBLike();
        dbLike.setUserId(like.getUserId());
        dbLike.setDateAndTimeCreated(MDateTimeConverter.convertMDateTimeToDBMDateTime(like.getDateAndTimeCreated()));
        return dbLike;
    }
}
