package saversandloaders.database.converters.userconverter.tweetconverter;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbtweetmodels.DBComment;
import saversandloaders.database.models.dbtweetmodels.DBLike;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweet;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweetComment;
import models.tweet.Comment;
import models.tweet.Like;
import models.tweet.Retweet;
import models.tweet.Tag;

import java.util.ArrayList;

public class RetweetCommentConverter {
    public static DBRetweetComment convertCommentToDBRetweetComment(Comment comment){
        DBRetweetComment dbRetweetComment = new DBRetweetComment();
        dbRetweetComment.setText(comment.getText());
        dbRetweetComment.setImagePath(comment.getImagePath());
        dbRetweetComment.setUserUsername(comment.getUserUsername());
        dbRetweetComment.setUserProfileImagePath(comment.getUserProfileImagePath());
        dbRetweetComment.setTimesReported(comment.getTimesReported());
        dbRetweetComment.setDateAndTimeCreated(MDateTimeConverter.convertMDateTimeToDBMDateTime(comment.getDateAndTimeCreated()));
        dbRetweetComment.setUserId(comment.getUserId());

        for (Tag tag : comment.getTags()){
            dbRetweetComment.getDbTags().add(TagConverter.convertTagToDBTag(tag));
        }

        for (Like like : comment.getLikes()){
            dbRetweetComment.getDbLikes().add(LikeConverter.convertLikeToDBLike(like));
        }

        for (Retweet retweet1 : comment.getRetweets()){
            dbRetweetComment.getDbRetweets().add(RetweetConverter.convertRetweetToDBRetweet(retweet1));
        }

        for (Comment comment1 : comment.getComments()){
            dbRetweetComment.getDbComments().add(CommentConverter.convertCommentTODBComment(comment1));
        }

        return dbRetweetComment;
    }

    public static Comment convertDBRetweetCommentToComment(DBRetweetComment dbRetweetComment){
        Comment comment = new Comment();
        comment.setText(dbRetweetComment.getText());
        comment.setImagePath(dbRetweetComment.getImagePath());
        comment.setUserId(dbRetweetComment.getUserId());
        comment.setUserUsername(dbRetweetComment.getUserUsername());
        comment.setUserProfileImagePath(dbRetweetComment.getUserProfileImagePath());
        comment.setTimesReported(dbRetweetComment.getTimesReported());

        comment.setDateAndTimeCreated(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbRetweetComment.getDateAndTimeCreated()));

        ArrayList<Tag> r = new ArrayList<>();
        for (DBTag dbTag : dbRetweetComment.getDbTags()){
            r.add(TagConverter.convertDBTagToTag(dbTag));
        }
        comment.setTags(r);

        ArrayList<Like> a = new ArrayList<>();
        for (DBLike dbLike : dbRetweetComment.getDbLikes()){
            a.add(LikeConverter.convertDBLikeToLike(dbLike));
        }
        comment.setLikes(a);

        ArrayList<Retweet> e = new ArrayList<>();
        for (DBRetweet dbRetweet1 : dbRetweetComment.getDbRetweets()){
            e.add(RetweetConverter.convertDBRetweetToRetweet(dbRetweet1));
        }
        comment.setRetweets(e);

        ArrayList<Comment> s = new ArrayList<>();
        for (DBComment dbComment : dbRetweetComment.getDbComments()){
            s.add(CommentConverter.convertDBCommentToComment(dbComment));
        }
        comment.setComments(s);

        return comment;
    }
}
