package saversandloaders.database.converters.userconverter.tweetconverter;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbtweetmodels.DBLike;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweet;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweetComment;
import models.tweet.Comment;
import models.tweet.Like;
import models.tweet.Retweet;
import models.tweet.Tag;

import java.util.ArrayList;

public class RetweetConverter {
    public static DBRetweet convertRetweetToDBRetweet(Retweet retweet){
        DBRetweet dbRetweet = new DBRetweet();
        dbRetweet.setText(retweet.getText());
        dbRetweet.setImagePath(retweet.getImagePath());
        dbRetweet.setUserUsername(retweet.getUserUsername());
        dbRetweet.setUserProfileImagePath(retweet.getUserProfileImagePath());
        dbRetweet.setTimesReported(retweet.getTimesReported());
        dbRetweet.setDateAndTimeCreated(MDateTimeConverter.convertMDateTimeToDBMDateTime(retweet.getDateAndTimeCreated()));
        dbRetweet.setUserId(retweet.getUserId());

        for (Tag tag : retweet.getTags()){
            dbRetweet.getDbTags().add(TagConverter.convertTagToDBTag(tag));
        }

        for (Like like : retweet.getLikes()){
            dbRetweet.getDbLikes().add(LikeConverter.convertLikeToDBLike(like));
        }

        for (Retweet retweet1 : retweet.getRetweets()){
            dbRetweet.getDbRetweets().add(RetweetConverter.convertRetweetToDBRetweet(retweet1));
        }

        for (Comment comment : retweet.getComments()){
            dbRetweet.getDbRetweetComments().add(RetweetCommentConverter.convertCommentToDBRetweetComment(comment));
        }

        return dbRetweet;
    }

    public static Retweet convertDBRetweetToRetweet(DBRetweet dbRetweet){
        Retweet retweet = new Retweet();
        retweet.setText(dbRetweet.getText());
        retweet.setImagePath(dbRetweet.getImagePath());
        retweet.setUserId(dbRetweet.getUserId());
        retweet.setUserUsername(dbRetweet.getUserUsername());
        retweet.setUserProfileImagePath(dbRetweet.getUserProfileImagePath());
        retweet.setTimesReported(dbRetweet.getTimesReported());

        retweet.setDateAndTimeCreated(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbRetweet.getDateAndTimeCreated()));

        ArrayList<Tag> r = new ArrayList<>();
        for (DBTag dbTag : dbRetweet.getDbTags()){
            r.add(TagConverter.convertDBTagToTag(dbTag));
        }
        retweet.setTags(r);

        ArrayList<Like> a = new ArrayList<>();
        for (DBLike dbLike : dbRetweet.getDbLikes()){
            a.add(LikeConverter.convertDBLikeToLike(dbLike));
        }
        retweet.setLikes(a);

        ArrayList<Retweet> e = new ArrayList<>();
        for (DBRetweet dbRetweet1 : dbRetweet.getDbRetweets()){
            e.add(RetweetConverter.convertDBRetweetToRetweet(dbRetweet1));
        }
        retweet.setRetweets(e);

        ArrayList<Comment> s = new ArrayList<>();
        for (DBRetweetComment dbRetweetComment : dbRetweet.getDbRetweetComments()){
            s.add(RetweetCommentConverter.convertDBRetweetCommentToComment(dbRetweetComment));
        }
        retweet.setComments(s);

        return retweet;
    }


}
