package saversandloaders.database.converters.userconverter;

import models.time.MDate;
import saversandloaders.database.converters.userconverter.mdatetimeconverter.MDateTimeConverter;
import saversandloaders.database.converters.userconverter.messagingmodelsconverters.ChatConverter;
import saversandloaders.database.converters.userconverter.messagingmodelsconverters.GroupConverter;
import saversandloaders.database.converters.userconverter.othermodelsconverters.FollowRequestConverter;
import saversandloaders.database.converters.userconverter.othermodelsconverters.RFollowRequestConverter;
import saversandloaders.database.converters.userconverter.othermodelsconverters.SystemMessageConverter;
import saversandloaders.database.converters.userconverter.tweetconverter.RetweetConverter;
import saversandloaders.database.converters.userconverter.tweetconverter.TagConverter;
import saversandloaders.database.converters.userconverter.tweetconverter.TweetConverter;
import saversandloaders.database.models.dbmessagingmodels.DBChat;
import saversandloaders.database.models.dbmessagingmodels.DBGroup;
import saversandloaders.database.models.dbothermodels.DBFollowRequest;
import saversandloaders.database.models.dbothermodels.DBRFollowRequest;
import saversandloaders.database.models.dbothermodels.DBSystemMessage;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import saversandloaders.database.models.dbtweetmodels.DBTweet;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweet;
import saversandloaders.database.models.dbusermodels.DBUser;
import models.messaging.Chat;
import models.messaging.Group;
import models.other.FollowRequest;
import models.other.SystemMassage;
import models.tweet.Retweet;
import models.tweet.Tag;
import models.tweet.Tweet;
import models.user.User;
import models.user.information.*;

import java.util.ArrayList;

public class UserConverter {

    public static DBUser convertUserToDBUser(User user){
        DBUser dbUser = new DBUser();

        dbUser.setId(user.getId());
        dbUser.setName(user.getName().getText());
        dbUser.setLastname(user.getLastname().getText());
        dbUser.setUsername(user.getUsername().getText());
        try {
            dbUser.setPreviosUsername(user.getPrevious_username().getText());
        }catch (NullPointerException ignored){}
        dbUser.setPassword(user.getPassword().getText());
        dbUser.setEmail(user.getEmail().getText());
        dbUser.setPhonenumber(user.getPhonenumber().getMainPart());
        dbUser.setBio(user.getBio().getText());
        dbUser.setProfileImagePath(user.getProfileImagePath());
        dbUser.setBirthdateYear(user.getBirthdate().getYear());
        dbUser.setBirthdateMonth(user.getBirthdate().getMonth());
        dbUser.setBirthdateDay(user.getBirthdate().getDay());
        dbUser.setActive(user.isActive());
        dbUser.setBirthdateVisible(user.isBirthdateVisible());
        dbUser.setPrivate(user.isPrivate());
        dbUser.setDisabled(user.isDisabled());
        dbUser.setDeleted(user.isDeleted());
        dbUser.setEverybodyCanSeeLastseen(user.isEverybodyCanSeeLastseen());
        dbUser.setNobodyCanSeeLastseen(user.isNobodyCanSeeLastseen());
        dbUser.setGameBotFollower(user.isGameBotFollower());
        dbUser.setQuestionBotFollower(user.isQuestionBotFollower());
        dbUser.setCalculatorBotFollower(user.isCalculatorBotFollower());


        dbUser.setLastseen(MDateTimeConverter.convertMDateTimeToDBMDateTime(user.getLastseen()));
//
//        DBMDate birthdate = new DBMDate();
//        birthdate.setYear(user.getBirthdate().getYear());
//        birthdate.setMonth(user.getBirthdate().getMonth());
//        birthdate.setDay(user.getBirthdate().getDay());
//        dbUser.setBirthdate(birthdate);
//
        for (Tweet tweet : user.getSavedmassages()){
            DBTweet dbTweet = TweetConverter.convertTweetToDBTweet(dbUser, tweet);
            dbTweet.setType(1);
            dbUser.getTweets().add(dbTweet);
        }

        for (Tweet tweet : user.getTweets()){
            DBTweet dbTweet = TweetConverter.convertTweetToDBTweet(dbUser, tweet);
            dbTweet.setType(2);
            dbUser.getTweets().add(dbTweet);
        }

        for (Retweet retweet : user.getRetweets()){
            dbUser.getRetweets().add(RetweetConverter.convertRetweetToDBRetweet(retweet));
        }

        for (Tweet tweet : user.getLikedTweets()){
            DBTweet dbTweet = TweetConverter.convertTweetToDBTweet(dbUser, tweet);
            dbTweet.setType(3);
            dbUser.getTweets().add(dbTweet);
        }

        for (Tweet tweet : user.getReceivedTweets()){
            DBTweet dbTweet = TweetConverter.convertTweetToDBTweet(dbUser, tweet);
            dbTweet.setType(4);
            dbUser.getTweets().add(dbTweet);
        }

        dbUser.setFollowingsId(user.getFollowingsId());
        dbUser.setFollowersId(user.getFollowersId());
        dbUser.setBlockedUsersId(user.getBlockedUsersId());
        dbUser.setMutedUsersId(user.getMuted_usersId());

        for (FollowRequest followRequest : user.getSentFollowRequests()){
            dbUser.getSentFollowRequests().add(FollowRequestConverter.convertFollowRequestToDBFollowRequest(followRequest));
        }

        for (FollowRequest followRequest : user.getReceivedFollowRequests()){
            dbUser.getReceivedFollowRequests().add(RFollowRequestConverter.convertFollowRequestToDBRFollowRequest(followRequest));
        }

        for (SystemMassage systemMassage : user.getSystemMassages()){
            dbUser.getSystemMassages().add(SystemMessageConverter.convertSystemMessageToDBSystemMessage(systemMassage));
        }

        for (Tag tag : user.getUsedTags()){
            dbUser.getUsedTags().add(TagConverter.convertTagToDBTag(tag));
        }

        for (Chat chat : user.getChats()){
            dbUser.getDbChats().add(ChatConverter.convertChatToDBChat(chat));
        }

        for (Chat chat : user.getChatsWithBots()){
            dbUser.getDbChats().add(ChatConverter.convertChatToDBChat(chat));
        }

        for (Group group : user.getGroups()){
            dbUser.getDbGroups().add(GroupConverter.convertGroupToDBGroup(group));
        }

        return dbUser;
    }

    public static User convertDBUserToUser(DBUser dbUser){
        User user = new User();

        user.setId(dbUser.getId());
        user.setName(new Name(dbUser.getId(), dbUser.getName()));
        user.setLastname(new LastName(dbUser.getId(), dbUser.getLastname()));
        user.setUsername(new UserName(dbUser.getId(), dbUser.getUsername()));
        user.setPrevious_username(new UserName(dbUser.getId(), dbUser.getPreviosUsername()));
        try {
            user.setPassword(new Password(dbUser.getId(), dbUser.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setEmail(new Email(dbUser.getId(), dbUser.getEmail()));
        user.setPhonenumber(new Phonenumber("98", dbUser.getPhonenumber(), dbUser.getId()));
        user.setBio(new Bio(dbUser.getId(), dbUser.getBio()));
        user.setBirthdate(new MDate(dbUser.getBirthdateYear(), dbUser.getBirthdateMonth(), dbUser.getBirthdateDay()));
        user.setProfileImagePath(dbUser.getProfileImagePath());
        user.setActive(dbUser.isActive());
        user.setBirthdateVisible(dbUser.isBirthdateVisible());
        user.setPrivate(dbUser.isPrivate());
        user.setDisabled(dbUser.isDisabled());
        user.setDeleted(dbUser.isDeleted());
        user.setEverybodyCanSeeLastseen(dbUser.isEverybodyCanSeeLastseen());
        user.setNobodyCanSeeLastseen(dbUser.isNobodyCanSeeLastseen());
        user.setGameBotFollower(dbUser.isGameBotFollower());
        user.setQuestionBotFollower(dbUser.isQuestionBotFollower());
        user.setCalculatorBotFollower(dbUser.isCalculatorBotFollower());

        user.setLastseen(MDateTimeConverter.convertDBMDateTimeToMDateTime(dbUser.getLastseen()));
//
//        MDate birthdate = new MDate();
//        birthdate.setYear(dbUser.getBirthdate().getYear());
//        birthdate.setMonth(dbUser.getBirthdate().getMonth());
//        birthdate.setDay(dbUser.getBirthdate().getDay());
//        user.setBirthdate(birthdate);
//
        ArrayList<Tweet> a = new ArrayList<>();
        for (DBTweet dbTweet : dbUser.getTweets()){
            if (dbTweet.getType() == 1){
                a.add(TweetConverter.convertDBTweetToTweet(dbTweet));
            }
        }
        user.setSavedmassages(a);

        ArrayList<Tweet> b = new ArrayList<>();
        for (DBTweet dbTweet : dbUser.getTweets()){
            if (dbTweet.getType() == 2){
                b.add(TweetConverter.convertDBTweetToTweet(dbTweet));
            }
        }
        user.setTweets(b);
//
        ArrayList<Retweet> c = new ArrayList<>();
        for (DBRetweet dbRetweet : dbUser.getRetweets()){
            c.add(RetweetConverter.convertDBRetweetToRetweet(dbRetweet));
        }
        user.setRetweets(c);

        ArrayList<Tweet> d = new ArrayList<>();
        for (DBTweet dbTweet : dbUser.getTweets()){
            if (dbTweet.getType() == 3){
                d.add(TweetConverter.convertDBTweetToTweet(dbTweet));
            }
        }
        user.setLikedTweets(d);

        ArrayList<Tweet> e = new ArrayList<>();
        for (DBTweet dbTweet : dbUser.getTweets()){
            if (dbTweet.getType() == 4){
                e.add(TweetConverter.convertDBTweetToTweet(dbTweet));
            }
        }
        user.setReceivedTweets(e);
//
        user.setFollowingsId(new ArrayList<>(dbUser.getFollowingsId()));
        user.setFollowersId(new ArrayList<>(dbUser.getFollowersId()));
        user.setBlockedUsersId(new ArrayList<>(dbUser.getBlockedUsersId()));
        user.setMuted_usersId(new ArrayList<>(dbUser.getMutedUsersId()));
//
        ArrayList<FollowRequest> l = new ArrayList<>();
        for (DBFollowRequest dbFollowRequest : dbUser.getSentFollowRequests()){
            l.add(FollowRequestConverter.convertDBFollowRequestToFollowRequest(dbFollowRequest));
        }
        user.setSentFollowRequests(l);

        ArrayList<FollowRequest> y = new ArrayList<>();
        for (DBRFollowRequest dbrFollowRequest : dbUser.getReceivedFollowRequests()){
            y.add(RFollowRequestConverter.convertDBRFollowRequestToFollowRequest(dbrFollowRequest));
        }
        user.setReceivedFollowRequests(y);

        ArrayList<SystemMassage> x = new ArrayList<>();
        for (DBSystemMessage dbSystemMessage : dbUser.getSystemMassages()){
            x.add(SystemMessageConverter.convertDBSystemMessageToSystemMessage(dbSystemMessage));
        }
        user.setSystemMassages(x);

        ArrayList<Tag> r = new ArrayList<>();
        for (DBTag dbTag : dbUser.getUsedTags()){
            r.add(TagConverter.convertDBTagToTag(dbTag));
        }
        user.setUsedTags(r);

        ArrayList<Chat> h = new ArrayList<>();
        ArrayList<Chat> w = new ArrayList<>();
        for (DBChat dbChat : dbUser.getDbChats()){
            if ((dbChat.getUser2Id() == -10) || (dbChat.getUser2Id() == -20) || (dbChat.getUser2Id() == -30)){
                w.add(ChatConverter.convertDBChatToChat(dbChat));
                continue;
            }
            h.add(ChatConverter.convertDBChatToChat(dbChat));
        }
        user.setChats(h);
        user.setChatsWithBots(w);

        ArrayList<Group> j = new ArrayList<>();
        for (DBGroup dbGroup : dbUser.getDbGroups()){
            j.add(GroupConverter.convertDBGroupToGroup(dbGroup));
        }
        user.setGroups(j);

        return user;
    }
}
