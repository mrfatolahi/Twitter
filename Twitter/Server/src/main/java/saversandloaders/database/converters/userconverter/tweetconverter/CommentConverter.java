package saversandloaders.database.converters.userconverter.tweetconverter;

import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.models.dbtweetmodels.DBComment;
import saversandloaders.database.models.dbtweetmodels.DBLike;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweet;
import models.tweet.Comment;
import models.tweet.Like;
import models.tweet.Retweet;
import models.tweet.Tag;

import java.util.ArrayList;

public class CommentConverter {
    public static DBComment convertCommentTODBComment(Comment comment){
        DBComment dbComment = new DBComment();

        dbComment.setUserId(comment.getUserId());
        dbComment.setText(comment.getText());
        dbComment.setImagePath(comment.getImagePath());
        dbComment.setUserUsername(comment.getUserUsername());
        dbComment.setUserProfileImagePath(comment.getUserProfileImagePath());
        dbComment.setTimesReported(comment.getTimesReported());
        dbComment.setDateAndTimeCreated(MDateTimeConverter.convertMDateTimeToDBMDateTime(comment.getDateAndTimeCreated()));
        dbComment.setUserId(comment.getUserId());

        for (Tag tag : comment.getTags()){
            dbComment.getDbTags().add(TagConverter.convertTagToDBTag(tag));
        }

        for (Like like : comment.getLikes()){
            dbComment.getDbLikes().add(LikeConverter.convertLikeToDBLike(like));
        }

        for (Retweet retweet : comment.getRetweets()){
            dbComment.getDbRetweets().add(RetweetConverter.convertRetweetToDBRetweet(retweet));
        }

        for (Comment comment1 : comment.getComments()){
            dbComment.getDbComments().add(CommentConverter.convertCommentTODBComment(comment1));
        }

        return dbComment;
    }

    public static Comment convertDBCommentToComment(DBComment dbComment){
        Comment comment = new Comment();
        comment.setText(dbComment.getText());
        comment.setImagePath(dbComment.getImagePath());
        comment.setUserId(dbComment.getUserId());
        comment.setUserUsername(dbComment.getUserUsername());
        comment.setUserProfileImagePath(dbComment.getUserProfileImagePath());
        comment.setTimesReported(dbComment.getTimesReported());

        comment.setDateAndTimeCreated(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbComment.getDateAndTimeCreated()));

        ArrayList<Tag> r = new ArrayList<>();
        for (DBTag dbTag : dbComment.getDbTags()){
            r.add(TagConverter.convertDBTagToTag(dbTag));
        }
        comment.setTags(r);

        ArrayList<Like> a = new ArrayList<>();
        for (DBLike dbLike : dbComment.getDbLikes()){
            a.add(LikeConverter.convertDBLikeToLike(dbLike));
        }
        comment.setLikes(a);

        ArrayList<Retweet> e = new ArrayList<>();
        for (DBRetweet dbRetweet1 : dbComment.getDbRetweets()){
            e.add(RetweetConverter.convertDBRetweetToRetweet(dbRetweet1));
        }
        comment.setRetweets(e);

        ArrayList<Comment> s = new ArrayList<>();
        for (DBComment dbComment1 : dbComment.getDbComments()){
            s.add(CommentConverter.convertDBCommentToComment(dbComment1));
        }
        comment.setComments(s);

        return comment;
    }
}
