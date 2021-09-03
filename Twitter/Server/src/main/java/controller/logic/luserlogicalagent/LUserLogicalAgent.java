package controller.logic.luserlogicalagent;

import XLogger.MLogger;
import controller.ClientHandler;
import controller.logic.luserlogicalagent.interpreters.MainInterPreter;
import events.serverevents.interfaces.LogicEventManager;
import controller.logic.LogicalAgent;
import events.serverevents.luser.personalpage.notificationspage.UpdateReceivedRequestsPage;
import events.serverevents.luser.personalpage.notificationspage.UpdateSentRequestsPage;
import events.serverevents.main.ServerEvent;
import events.serverevents.luser.graphicalmodels.graphicalprofile.FollowingResult;
import events.serverevents.luser.graphicalmodels.graphicalprofile.FollowingResultEvent;
import events.clientevents.graphicalevents.loggeduser.LUserGraphicalEvent;
import events.clientevents.main.ClientEvent;
import models.messaging.Chat;
import models.messaging.Group;
import models.other.FollowRequest;
import models.other.SystemMassage;
import models.other.hyperlink.HyperLink;
import models.other.hyperlink.HyperLinkType;
import models.time.MDateTime;
import models.tweet.*;
import models.user.User;
import saversandloaders.loaders.UserLoader;
import saversandloaders.savers.Saver;
import saversandloaders.savers.UserSaver;

import java.io.IOException;
import java.util.ArrayList;

public class LUserLogicalAgent implements LogicEventManager {
    private final User user;
    private final LogicalAgent logicalAgent;
    private final MainInterPreter mainInterPreter;

    public LUserLogicalAgent(User user, LogicalAgent logicalAgent) throws IOException {
        this.user = user;
        this.logicalAgent = logicalAgent;
        this.mainInterPreter = new MainInterPreter(this);
        user.setLastseen(MDateTime.now());
        UserSaver.saveUser(user);
    }

    public void updateInfFromFile() throws IOException {
        User lodedUser = UserLoader.loadUser(user.getId());
        user.setName(lodedUser.getName());
        user.setLastname(lodedUser.getLastname());
        user.setUsername(lodedUser.getUsername());
        user.setPrevious_username(lodedUser.getPrevious_username());
        user.setBirthdate(lodedUser.getBirthdate());
        user.setPassword(lodedUser.getPassword());
        user.setBirthdate(lodedUser.getBirthdate());
        user.setEmail(lodedUser.getEmail());
        user.setPhonenumber(lodedUser.getPhonenumber());
        user.setBio(lodedUser.getBio());
        user.setLastseen(lodedUser.getLastseen());
        user.setBirthdateVisible(lodedUser.isBirthdateVisible());
        user.setPrivate(lodedUser.isPrivate());
        user.setActive(lodedUser.isActive());
        user.setTweets(lodedUser.getTweets());
        user.setSavedmassages(lodedUser.getSavedmassages());
        user.setRetweets(lodedUser.getRetweets());
        user.setLikedTweets(lodedUser.getLikedTweets());
        user.setReceivedTweets(lodedUser.getReceivedTweets());
        user.setFollowingsId(lodedUser.getFollowingsId());
        user.setFollowersId(lodedUser.getFollowersId());
        user.setBlockedUsersId(lodedUser.getBlockedUsersId());
        user.setMuted_usersId(lodedUser.getMuted_usersId());
        user.setSentFollowRequests(lodedUser.getSentFollowRequests());
        user.setReceivedFollowRequests(lodedUser.getReceivedFollowRequests());
        user.setSystemMassages(lodedUser.getSystemMassages());
        user.setUsedTags(lodedUser.getUsedTags());
        user.setChats(lodedUser.getChats());
        user.setGroups(lodedUser.getGroups());
        user.setChatsWithBots(lodedUser.getChatsWithBots());
        user.setDisabled(lodedUser.isDisabled());
        user.setDeleted(lodedUser.isDeleted());
        user.setNobodyCanSeeLastseen(lodedUser.isNobodyCanSeeLastseen());
        user.setEverybodyCanSeeLastseen(lodedUser.isEverybodyCanSeeLastseen());
        user.setGameBotFollower(lodedUser.isGameBotFollower());
        user.setQuestionBotFollower(lodedUser.isQuestionBotFollower());
        user.setCalculatorBotFollower(lodedUser.isCalculatorBotFollower());
    }

    public User getUser() {
        return user;
    }

    public LogicalAgent getLogicalAgent() {
        return logicalAgent;
    }

    public void addTweet(Tweet tweet) throws IOException {
        ArrayList<Tag> userUsedtags= new ArrayList<Tag>(user.getUsedTags());
        userUsedtags.addAll(tweet.getTags());
        user.setUsedTags(userUsedtags);
        user.getTweets().add(tweet);
        UserSaver.saveUser(user);
    }

    public void followThis(User userToFollow) throws IOException {
        if(userToFollow.getBlockedUsersId().contains(user.getId())){
            sendLogicalEvent(new FollowingResultEvent(FollowingResult.USER_HAS_BLOCKED_REQUESTER));
            return;
        }
        if(user.getFollowingsId().contains(userToFollow.getId())){
            sendLogicalEvent(new FollowingResultEvent(FollowingResult.ALREADY_FOLLOWED));
            return;
        }

        if(userToFollow.isPrivate()){
            FollowRequest followRequest = new FollowRequest(user, userToFollow, user.getUsername().getText()+" wants to follow you", userToFollow.getUsername().getText());
            userToFollow.getReceivedFollowRequests().add(followRequest);
            user.getSentFollowRequests().add(followRequest);
            UserSaver.saveUser(userToFollow);
            UserSaver.saveUser(user);
            try {
                getClientById(userToFollow.getId()).updateLoggedInUserInfoFromFile();
            }catch (NullPointerException ignored){}
            sendLogicalEvent(new FollowingResultEvent(FollowingResult.FOLLOWING_REQUEST_SENT));
        }else {
            user.getFollowingsId().add(userToFollow.getId());
            userToFollow.getFollowersId().add(user.getId());

            UserSaver.saveUser(userToFollow);
            UserSaver.saveUser(user);
            try {
                getClientById(userToFollow.getId()).updateLoggedInUserInfoFromFile();
            }catch (NullPointerException ignored){}
            sendLogicalEvent(new FollowingResultEvent(FollowingResult.DONE));
        }
    }

    public void followBot(int botId) throws IOException {
        if (botId == -10){
            user.setGameBotFollower(true);
            UserSaver.saveUser(user);
            logicalAgent.getClientHandler().getMainController().getBotController().getGameBotsManager().addFollower(user.getId());
            sendLogicalEvent(new FollowingResultEvent(FollowingResult.DONE));
        } else
        if (botId == -20){
            user.setQuestionBotFollower(true);
            UserSaver.saveUser(user);
            logicalAgent.getClientHandler().getMainController().getBotController().getQuestionBotManager().getQuestionBotInfo().getFollowersId().add(user.getId());
            Saver.saveQuestionBotInfo(logicalAgent.getClientHandler().getMainController().getBotController().getQuestionBotManager().getQuestionBotInfo());
            sendLogicalEvent(new FollowingResultEvent(FollowingResult.DONE));
        } else
        if (botId == -30){
            user.setCalculatorBotFollower(true);
            UserSaver.saveUser(user);
            logicalAgent.getClientHandler().getMainController().getBotController().getCalculatorBotManager().getCalculatorBotInfo().getFollowersId().add(user.getId());
            Saver.saveCalculatorBotInfo(logicalAgent.getClientHandler().getMainController().getBotController().getCalculatorBotManager().getCalculatorBotInfo());
            sendLogicalEvent(new FollowingResultEvent(FollowingResult.DONE));
        }
    }

    public void unfollowThis(User userToFollow) throws IOException {
        for(int i = 0 ; i < user.getFollowingsId().size() ; i++){
            if (user.getFollowingsId().get(i) == userToFollow.getId()){user.getFollowingsId().remove(i); break;}
        }
        for(int i = 0 ; i < userToFollow.getFollowersId().size() ; i++){
            if (userToFollow.getFollowersId().get(i) == user.getId()){userToFollow.getFollowersId().remove(i); break;}
        }
        SystemMassage systemMassage = new SystemMassage(user, userToFollow, user.getUsername()+" unfollowed you");
        userToFollow.getSystemMassages().add(systemMassage);
        UserSaver.saveUser(userToFollow);
        UserSaver.saveUser(user);
        try {
            getClientById(userToFollow.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
    }

    public void unFollowBot(int botId) throws IOException {
        if (botId == -10){
            user.setGameBotFollower(false);
            UserSaver.saveUser(user);
            logicalAgent.getClientHandler().getMainController().getBotController().getGameBotsManager().getGameBotInfo().getFollowersId().remove(user.getId());
            Saver.saveGameBotInfo(logicalAgent.getClientHandler().getMainController().getBotController().getGameBotsManager().getGameBotInfo());
        } else
        if (botId == -20){
            user.setQuestionBotFollower(false);
            UserSaver.saveUser(user);
            logicalAgent.getClientHandler().getMainController().getBotController().getQuestionBotManager().getQuestionBotInfo().getFollowersId().remove(user.getId());
            Saver.saveQuestionBotInfo(logicalAgent.getClientHandler().getMainController().getBotController().getQuestionBotManager().getQuestionBotInfo());
        } else
        if (botId == -30){
            user.setCalculatorBotFollower(false);
            UserSaver.saveUser(user);
            logicalAgent.getClientHandler().getMainController().getBotController().getCalculatorBotManager().getCalculatorBotInfo().getFollowersId().remove(user.getId());
            Saver.saveCalculatorBotInfo(logicalAgent.getClientHandler().getMainController().getBotController().getCalculatorBotManager().getCalculatorBotInfo());
        }
    }

    public void blockThis(User userToBlock) throws IOException {
        user.getBlockedUsersId().add(userToBlock.getId());
        UserSaver.saveUser(user);
    }

    public void blockThis(int userId) throws IOException {
        user.getBlockedUsersId().add(userId);
        UserSaver.saveUser(user);
    }

    public void muteThis(User userToMute) throws IOException {
        user.getMuted_usersId().add(userToMute.getId());
        UserSaver.saveUser(user);
    }

    public void muteThis(int userId) throws IOException {
        user.getMuted_usersId().add(userId);
        UserSaver.saveUser(user);
    }

    public void unblockThis(User userToUnBlock) throws IOException {
        for(int i = 0 ; i < user.getMuted_usersId().size() ; i++){
            if (user.getMuted_usersId().get(i) == userToUnBlock.getId()){user.getMuted_usersId().remove(i); break;}
        }
        UserSaver.saveUser(userToUnBlock);
        UserSaver.saveUser(user);
        try {
            getClientById(userToUnBlock.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
    }

    public void makeAccountPrivate() throws IOException {
        user.setPrivate(true);
        UserSaver.saveUser(user);
    }

    public void makeAccountPublic() throws IOException {
        user.setPrivate(false);
        UserSaver.saveUser(user);
    }

    public Chat startChatWith(int userToChatId) throws IOException {
        User userToChat = UserLoader.loadUser(userToChatId);
        Chat chat = new Chat(user.getId(), userToChat.getId(), user.getUsername().getText(), UserLoader.loadUser(userToChatId).getUsername().getText());
        user.getChats().add(chat);
        userToChat.getChats().add(chat);
        UserSaver.saveUser(userToChat);
        UserSaver.saveUser(user);
        try {
            getClientById(userToChat.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        return chat;
    }

    public void disableAccount() throws IOException {
        user.setActive(false);
        UserSaver.saveUser(user);
    }

    public void activeAccount() throws IOException {
        user.setActive(true);
        UserSaver.saveUser(user);
    }

    public ArrayList<Tweet> getTimelineTweets() throws IOException {
        ArrayList<Tweet> timelineTweets = new ArrayList<>(user.getTweets());
        for(int followingId : user.getFollowingsId()) {
            timelineTweets.addAll(UserLoader.loadUser(followingId).getTweets());
            timelineTweets.addAll(UserLoader.loadUser(followingId).getRetweets());
            timelineTweets.addAll(UserLoader.loadUser(followingId).getLikedTweets());
        }

        if (user.isGameBotFollower()){
            timelineTweets.addAll(logicalAgent.getClientHandler().getMainController().getBotController().getGameBotsManager().getGameBotInfo().getTweets());
        }
        if (user.isQuestionBotFollower()){
            timelineTweets.addAll(logicalAgent.getClientHandler().getMainController().getBotController().getQuestionBotManager().getQuestionBotInfo().getTweets());
        }
        if (user.isCalculatorBotFollower()){
            timelineTweets.addAll(logicalAgent.getClientHandler().getMainController().getBotController().getCalculatorBotManager().getCalculatorBotInfo().getTweets());
        }

        return timelineTweets;
    }

    public void likeTweet(Tweet tweet) throws IOException {
        User tweetOwner = UserLoader.loadUser(tweet.getUserId());
        for(Tweet tweet1 : tweetOwner.getTweets()){
            if(tweet1.getId() == tweet.getId()){
                Like like = new Like(user, tweet1.getId(), MDateTime.now());
                tweet1.getLikes().add(like);
                user.getLikedTweets().add(tweet1);
                UserSaver.saveUser(user);
                UserSaver.saveUser(tweetOwner);
                try {
                    getClientById(tweetOwner.getId()).updateLoggedInUserInfoFromFile();
                }catch (NullPointerException ignored){}
                break;
            }
        }
        this.updateInfFromFile();
    }

    public void commentTweet(Tweet tweet, String text) throws IOException {
        User tweetOwner = UserLoader.loadUser(tweet.getUserId());
        for(Tweet tweet1 : tweetOwner.getTweets()){
            if(tweet1.getId() == tweet.getId()){
                Comment comment = new Comment(text, user, "");
                tweet1.getComments().add(comment);
                user.getLikedTweets().add(tweet1);
                UserSaver.saveUser(user);
                UserSaver.saveUser(tweetOwner);
                try {
                    getClientById(tweetOwner.getId()).updateLoggedInUserInfoFromFile();
                }catch (NullPointerException ignored){}
                break;
            }
        }
        this.updateInfFromFile();
    }

    public void retweetTweet(Tweet tweet, String text, String tags, String imagePath) throws IOException {
        User tweetOwner = UserLoader.loadUser(tweet.getUserId());
        for(Tweet tweet1 : tweetOwner.getTweets()){
            if(tweet1.getId() == tweet.getId()){
                Retweet retweet = new Retweet(tweet.getId(), text, user, "", imagePath);
                tweet1.getRetweets().add(retweet);
                user.getLikedTweets().add(tweet1);
                UserSaver.saveUser(user);
                UserSaver.saveUser(tweetOwner);
                try {
                    getClientById(tweetOwner.getId()).updateLoggedInUserInfoFromFile();
                }catch (NullPointerException ignored){}
                break;
            }
        }
        this.updateInfFromFile();
    }

    public void forwardTweet(Tweet tweet, String receiverUsername) throws IOException {
        User user1 = UserLoader.loadUser(receiverUsername);
        user1.getReceivedTweets().add(tweet);
        UserSaver.saveUser(user1);
        try {
            getClientById(user1.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
    }

    public void reportTweet(Tweet tweet) throws IOException {
        User tweetOwner = UserLoader.loadUser(tweet.getUserId());
        for(Tweet tweet1 : tweetOwner.getTweets()){
            if(tweet1.getId() == tweet.getId()){
                tweet1.setTimesReported(tweet1.getTimesReported() + 1);
                if (tweet1.getTimesReported() == 5){
                    tweetOwner.getTweets().remove(tweet1);
                    UserSaver.saveUser(user);
                    UserSaver.saveUser(tweetOwner);
                    try {
                        getClientById(tweetOwner.getId()).updateLoggedInUserInfoFromFile();
                    }catch (NullPointerException ignored){}
                    break;
                }
                UserSaver.saveUser(user);
                UserSaver.saveUser(tweetOwner);
                try {
                    getClientById(tweetOwner.getId()).updateLoggedInUserInfoFromFile();
                }catch (NullPointerException ignored){}
                break;
            }
        }
        this.updateInfFromFile();
    }

    public void blockTweetsOwner(Tweet tweet) throws IOException {
        logicalAgent.getlUserLogicalAgent().blockThis(UserLoader.loadUser(tweet.getUserId()));
    }

    public void muteTweetsOwner(Tweet tweet) throws IOException {
        logicalAgent.getlUserLogicalAgent().muteThis(UserLoader.loadUser(tweet.getUserId()));
    }

    public ArrayList<Tweet> gethottweetsTweets() throws IOException {
        ArrayList<Tweet> hotTweetsTweets = new ArrayList<>(user.getTweets());
        for(int followingId : user.getFollowingsId()) {
            if(!UserLoader.loadUser(followingId).isDeleted() && !UserLoader.loadUser(followingId).isDisabled()) {
                hotTweetsTweets.addAll(UserLoader.loadUser(followingId).getTweets());
                hotTweetsTweets.addAll(UserLoader.loadUser(followingId).getRetweets());
                hotTweetsTweets.addAll(UserLoader.loadUser(followingId).getLikedTweets());
            }
        }

        if (user.isGameBotFollower()){
            hotTweetsTweets.addAll(logicalAgent.getClientHandler().getMainController().getBotController().getGameBotsManager().getGameBotInfo().getTweets());
        }
        if (user.isQuestionBotFollower()){
            hotTweetsTweets.addAll(logicalAgent.getClientHandler().getMainController().getBotController().getQuestionBotManager().getQuestionBotInfo().getTweets());
        }
        if (user.isCalculatorBotFollower()){
            hotTweetsTweets.addAll(logicalAgent.getClientHandler().getMainController().getBotController().getCalculatorBotManager().getCalculatorBotInfo().getTweets());
        }

        return hotTweetsTweets;
    }

    public ArrayList<User> getBlockedUsers() throws IOException {
        ArrayList<User> blockedUsers = new ArrayList<>();
        for (int id : user.getBlockedUsersId()){
            blockedUsers.add(UserLoader.loadUser(id));
        }
        return blockedUsers;
    }

    public ArrayList<User> getFollowers() throws IOException {
        ArrayList<User> followers = new ArrayList<>();
        for (int id : user.getFollowersId()){
            followers.add(UserLoader.loadUser(id));
        }
        return followers;
    }

    public ArrayList<User> getFollowings() throws IOException {
        ArrayList<User> followings = new ArrayList<>();
        for (int id : user.getFollowingsId()){
            followings.add(UserLoader.loadUser(id));
        }
        return followings;
    }

    public void acceptFollowingRequest(FollowRequest followRequest) throws Exception {
        User sender = UserLoader.loadUser(followRequest.getSenderId());
        User receiver = UserLoader.loadUser(followRequest.getReceiverId());
        followRequest.setAccepted(true);
        sender.followThisFromRequest(receiver);
        UserSaver.saveUser(receiver);
        try {
            getClientById(receiver.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        UserSaver.saveUser(user);
        for(int i = 0 ; i < receiver.getReceivedFollowRequests().size() ; i++){
            if((receiver.getReceivedFollowRequests().get(i).getSenderId() == followRequest.getSenderId()) &&
                    (receiver.getReceivedFollowRequests().get(i).getReceiverId() == followRequest.getReceiverId())){
                receiver.getReceivedFollowRequests().remove(i);
                break;
            }
        }
        for(int i = 0 ; i < sender.getSentFollowRequests().size() ; i++){
            if((sender.getSentFollowRequests().get(i).getSenderId() == followRequest.getSenderId()) &&
                    (sender.getSentFollowRequests().get(i).getReceiverId() == followRequest.getReceiverId())){
                sender.getSentFollowRequests().get(i).setAccepted(true);
                break;
            }
        }
        UserSaver.saveUser(sender);
        UserSaver.saveUser(receiver);
        try {
            getClientById(sender.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        try {
            getClientById(receiver.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        getClientById(sender.getId()).sendLogicalEvent(new UpdateSentRequestsPage());
        getClientById(receiver.getId()).sendLogicalEvent(new UpdateReceivedRequestsPage());
    }

    public void declineFollowRequestWithoutNotif(FollowRequest followRequest) throws InterruptedException, IOException {
        User receiver = UserLoader.loadUser(followRequest.getReceiverId());
        for(int i = 0 ; i < receiver.getReceivedFollowRequests().size() ; i++){
            if(receiver.getReceivedFollowRequests().get(i).getSenderId() == followRequest.getSenderId() &&
                    receiver.getReceivedFollowRequests().get(i).getReceiverId() == followRequest.getReceiverId()){
                receiver.getReceivedFollowRequests().remove(i);
                break;
            }
        }
        UserSaver.saveUser(receiver);
        try {
            getClientById(receiver.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        getClientById(followRequest.getSenderId()).sendLogicalEvent(new UpdateSentRequestsPage());
        getClientById(followRequest.getReceiverId()).sendLogicalEvent(new UpdateReceivedRequestsPage());
    }

    public void declineFollowRequestWithNotif(FollowRequest followRequest) throws InterruptedException, IOException {
        User sender = UserLoader.loadUser(followRequest.getSenderId());
        User receiver = UserLoader.loadUser(followRequest.getReceiverId());
        for(int i = 0 ; i < receiver.getReceivedFollowRequests().size() ; i++){
            if(receiver.getReceivedFollowRequests().get(i).getSenderId() == followRequest.getSenderId() &&
                    receiver.getReceivedFollowRequests().get(i).getReceiverId() == followRequest.getReceiverId()){
                receiver.getReceivedFollowRequests().remove(i);
                break;
            }
        }
        SystemMassage systemMassage = new SystemMassage(receiver, sender, UserLoader.loadUser(followRequest.getReceiverId()).getUsername().getText()+" declined your following request");
        sender.addSystemMassage(systemMassage);

        UserSaver.saveUser(receiver);
        UserSaver.saveUser(sender);
        try {
            getClientById(receiver.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        try {
            getClientById(sender.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        getClientById(followRequest.getSenderId()).sendLogicalEvent(new UpdateSentRequestsPage());
        getClientById(followRequest.getReceiverId()).sendLogicalEvent(new UpdateReceivedRequestsPage());
    }

    public void createNewGroup(String groupName, ArrayList<String> membersUsernames) throws IOException {
        Group group = new Group(groupName);
        for (String memberUsername : membersUsernames){
            group.addMember(UserLoader.loadUser(memberUsername).getId(), memberUsername);
        }

        group.addMember(user.getId(), user.getUsername().getText());

        user.getGroups().add(group);
        UserSaver.saveUser(user);

        for (String memberUsername : membersUsernames){
            User member = UserLoader.loadUser(memberUsername);
            member.getGroups().add(group);
            UserSaver.saveUser(member);
            try {
                getClientById(member.getId()).updateLoggedInUserInfoFromFile();
            }catch (NullPointerException ignored){}
        }

        for (String memberUsername : membersUsernames){
            User member = UserLoader.loadUser(memberUsername);
            try {
                getClientById(member.getId()).updateLoggedInUserInfoFromFile();
            }catch (Exception ignored){}
        }
    }

    public void spread(String text, ArrayList<String> receiversUsernames) throws IOException {
        System.out.println(receiversUsernames);
        for (String receiverUsername : receiversUsernames){
            for (Chat chat : user.getChats()){
                if (chat.getUser1Username().equals(receiverUsername) || chat.getUser2Username().equals(receiverUsername)){
                    chat.sendMassage(user.getId(), text, null, null);
                    User receiver1 = UserLoader.loadUser(receiverUsername);
                    for(int i = 0 ; i < receiver1.getChats().size() ; i++){
                        if(receiver1.getChats().get(i).getUser1Id() == chat.getUser1Id() && receiver1.getChats().get(i).getUser2Id() == chat.getUser2Id()){
                            ArrayList<Chat> ch = new ArrayList<>(receiver1.getChats());
                            ch.remove(i);
                            ch.add(chat);
                            receiver1.setChats(ch);
                            break;
                        }
                    }
                    UserSaver.saveUser(receiver1); UserSaver.saveUser(user);
                    try {
                        getClientById(receiver1.getId()).updateLoggedInUserInfoFromFile();
                    }catch (NullPointerException ignored){}
                }
            }
        }
    }

    public void addTweetToSomeBodySavedMessages(int userId, Tweet tweet) throws IOException {
        User user = UserLoader.loadUser(userId);
        user.getSavedmassages().add(tweet);
        UserSaver.saveUser(user);
        try {
            getClientById(user.getId()).updateLoggedInUserInfoFromFile();
        }catch (NullPointerException ignored){}
        updateInfFromFile();
    }

    public ClientHandler getClientById(int id){
        return logicalAgent.getClientById(id);
    }

    public HyperLink generateHyperLink(String text, String destination, HyperLinkType type){

        String token = "";
        switch (type){
            case PERSON_CHAT_LINK:
                token = destination;
                break;
            case GROUP_LINK:
                for (Group group : user.getGroups()){
                    if (group.getName().equals(destination)){
                        token = group.getToken();
                        break;
                    }
                }
                break;
            case BOT_CHAT_LINK:
                switch (destination){
                    case "GameBot":
                        token = "-10";
                        break;
                    case "QuestionBot":
                        token = "-20";
                        break;
                    case "CalculatorBot":
                        token = "-30";
                        break;
                }
            case GROUP_INVITE_LINK:
                for (Group group : user.getGroups()){
                    if (group.getName().equals(destination)){
                        token = group.getToken();
                        break;
                    }
                }
                break;
        }

        return new HyperLink(text, token, type);
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        logicalAgent.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception   {
        MLogger.log("Event: "+clientEvent.getClass().toString());
        mainInterPreter.receiveGraphicalEvent((LUserGraphicalEvent) clientEvent);
    }
}