package saversandloaders.database.converters.userconverter.tweetconverter;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbtweetmodels.DBComment;
import saversandloaders.database.models.dbtweetmodels.DBLike;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import saversandloaders.database.models.dbtweetmodels.DBTweet;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweet;
import saversandloaders.database.models.dbusermodels.DBUser;
import models.tweet.*;

import java.util.ArrayList;

public class TweetConverter {

    public static DBTweet convertTweetToDBTweet(DBUser owner, Tweet tweet){
        DBTweet dbTweet = new DBTweet();

        dbTweet.setUserId(tweet.getUserId());
        dbTweet.setText(tweet.getText());
        dbTweet.setImagePath(tweet.getImagePath());
        dbTweet.setUserUsername(tweet.getUserUsername());
        dbTweet.setUserProfileImagePath(tweet.getUserProfileImagePath());
        dbTweet.setTimesReported(tweet.getTimesReported());
        dbTweet.setDateAndTimeCreated(MDateTimeConverter.convertMDateTimeToDBMDateTime(tweet.getDateAndTimeCreated()));

        for (Tag tag : tweet.getTags()){
            dbTweet.getDbTags().add(TagConverter.convertTagToDBTag(tag));
        }

        for (Like like : tweet.getLikes()){
            dbTweet.getDbLikes().add(LikeConverter.convertLikeToDBLike(like));
        }

        for (Retweet retweet : tweet.getRetweets()){
            dbTweet.getDbRetweets().add(RetweetConverter.convertRetweetToDBRetweet(retweet));
        }

        for (Comment comment : tweet.getComments()){
            dbTweet.getDbComments().add(CommentConverter.convertCommentTODBComment(comment));
        }

        return dbTweet;
    }

    public static Tweet convertDBTweetToTweet(DBTweet dbTweet){
        Tweet tweet = new Tweet();

        tweet.setText(dbTweet.getText());
        tweet.setImagePath(dbTweet.getImagePath());
        tweet.setUserId(dbTweet.getUserId());
        tweet.setUserUsername(dbTweet.getUserUsername());
        tweet.setUserProfileImagePath(dbTweet.getUserProfileImagePath());
        tweet.setTimesReported(dbTweet.getTimesReported());

        tweet.setDateAndTimeCreated(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbTweet.getDateAndTimeCreated()));

        ArrayList<Tag> r = new ArrayList<>();
        for (DBTag dbTag : dbTweet.getDbTags()){
            r.add(TagConverter.convertDBTagToTag(dbTag));
        }
        tweet.setTags(r);

        ArrayList<Like> a = new ArrayList<>();
        for (DBLike dbLike : dbTweet.getDbLikes()){
            a.add(LikeConverter.convertDBLikeToLike(dbLike));
        }
        tweet.setLikes(a);

        ArrayList<Retweet> e = new ArrayList<>();
        for (DBRetweet dbRetweet1 : dbTweet.getDbRetweets()){
            e.add(RetweetConverter.convertDBRetweetToRetweet(dbRetweet1));
        }
        tweet.setRetweets(e);

        ArrayList<Comment> s = new ArrayList<>();
        for (DBComment dbComment : dbTweet.getDbComments()){
            s.add(CommentConverter.convertDBCommentToComment(dbComment));
        }
        tweet.setComments(s);

        return tweet;
    }
}
