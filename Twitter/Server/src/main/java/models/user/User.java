package models.user;

import config.Config;
import events.serverevents.luser.personalpage.infopage.SendUserInfoForPP;
import events.serverevents.luser.explorerpage.UserInfoServerEvent;
import models.messaging.Chat;
import models.messaging.Group;
import models.other.FollowRequest;
import models.other.SystemMassage;
import models.time.MDate;
import models.time.MDateTime;
import models.tweet.Retweet;
import models.tweet.Tag;
import models.tweet.Tweet;
import models.user.information.*;
import saversandloaders.UserIdGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class User {
    private int Id;
    private Name name;
    private LastName lastname;
    private UserName username;
    private UserName previous_username;
    private Password password;
    private MDate birthdate;
    private Email email;
    private Phonenumber phonenumber;
    private Bio bio;
    private MDateTime lastseen;
    private String profileImagePath;
    private boolean active;
    private ArrayList<Tweet> savedmassages;
    private ArrayList<Tweet> tweets;
    private ArrayList<Retweet> retweets;
    private ArrayList<Tweet> LikedTweets;
    private ArrayList<Tweet> receivedTweets;
    private ArrayList<Integer> followingsId;
    private ArrayList<Integer> followersId;
    private ArrayList<Integer> blockedUsersId;
    private ArrayList<Integer> muted_usersId;
    private ArrayList<FollowRequest> sentFollowRequests;
    private ArrayList<FollowRequest> receivedFollowRequests;
    private ArrayList<SystemMassage> systemMassages;
    private ArrayList<Tag> used_tags;
    private ArrayList<Chat> chats;
    private ArrayList<Chat> chatsWithBots;
    private ArrayList<Group> groups;
    private boolean birthdate_visibility;
    private boolean Private;
    private boolean disabled;
    private boolean deleted;
    private boolean everybodyCanSeeLastseen;
    private boolean nobodyCanSeeLastseen;
    private boolean gameBotFollower;
    private boolean questionBotFollower;
    private boolean calculatorBotFollower;


    public User() {
    }

    public User(Name name, LastName lastname, UserName username, Password password, MDate birthdate, Email email, Phonenumber phonenumber, Bio bio) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.phonenumber = phonenumber;
        this.bio = bio;
        this.Id = UserIdGenerator.generateAnId();
        this.lastseen = MDateTime.now();
        this.birthdate_visibility = false;
        this.Private = false;
        this.active = true;
        this.tweets = new ArrayList<Tweet>();
        this.retweets = new ArrayList<Retweet>();
        this.LikedTweets = new ArrayList<Tweet>();
        this.savedmassages = new ArrayList<Tweet>();
        this.receivedTweets = new ArrayList<Tweet>();
        this.followingsId = new ArrayList<Integer>();
        this.followersId = new ArrayList<Integer>();
        this.blockedUsersId = new ArrayList<Integer>();
        this.muted_usersId = new ArrayList<Integer>();
        this.sentFollowRequests = new ArrayList<FollowRequest>();
        this.receivedFollowRequests = new ArrayList<FollowRequest>();
        this.systemMassages = new ArrayList<SystemMassage>();
        this.used_tags = new ArrayList<Tag>();
        this.chats = new ArrayList<Chat>();
        this.groups = new ArrayList<Group>();
        this.chatsWithBots = new ArrayList<>();
        this.disabled = false;
        this.deleted = false;
        this.everybodyCanSeeLastseen = false;
        this.nobodyCanSeeLastseen = false;
        this.gameBotFollower = false;
        Config imageConfig = Config.getConfig("ImagesPro");
        this.profileImagePath = imageConfig.getProperty("defaultProfilePicture");
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public LastName getLastname() {
        return lastname;
    }

    public void setLastname(LastName lastname) {
        this.lastname = lastname;
    }

    public UserName getUsername() {
        return username;
    }

    public void setUsername(UserName username) {
        this.username = username;
    }

    public UserName getPrevious_username() {
        return previous_username;
    }

    public void setPrevious_username(UserName previous_username) {
        this.previous_username = previous_username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public MDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(MDate birthdate) {
        this.birthdate = birthdate;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phonenumber getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Phonenumber phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Bio getBio() {
        return bio;
    }

    public void setBio(Bio bio) {
        this.bio = bio;
    }

    public MDateTime getLastseen() {
        return lastseen;
    }

    public void setLastseen(MDateTime lastseen) {
        this.lastseen = lastseen;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Tweet> getSavedmassages() {
        return savedmassages;
    }

    public void setSavedmassages(ArrayList<Tweet> savedmassages) {
        this.savedmassages = savedmassages;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<Retweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(ArrayList<Retweet> retweets) {
        this.retweets = retweets;
    }

    public ArrayList<Tweet> getLikedTweets() {
        return LikedTweets;
    }

    public void setLikedTweets(ArrayList<Tweet> likedTweets) {
        LikedTweets = likedTweets;
    }

    public ArrayList<Tweet> getReceivedTweets() {
        return receivedTweets;
    }

    public void setReceivedTweets(ArrayList<Tweet> receivedTweets) {
        this.receivedTweets = receivedTweets;
    }

    public ArrayList<Integer> getFollowingsId() {
        return followingsId;
    }

    public void setFollowingsId(ArrayList<Integer> followingsId) {
        this.followingsId = followingsId;
    }

    public ArrayList<Integer> getFollowersId() {
        return followersId;
    }

    public void setFollowersId(ArrayList<Integer> followersId) {
        this.followersId = followersId;
    }

    public ArrayList<Integer> getBlockedUsersId() {
        return blockedUsersId;
    }

    public void setBlockedUsersId(ArrayList<Integer> blockedUsersId) {
        this.blockedUsersId = blockedUsersId;
    }

    public ArrayList<Integer> getMuted_usersId() {
        return muted_usersId;
    }

    public void setMuted_usersId(ArrayList<Integer> muted_usersId) {
        this.muted_usersId = muted_usersId;
    }

    public ArrayList<FollowRequest> getSentFollowRequests() {
        return sentFollowRequests;
    }

    public void setSentFollowRequests(ArrayList<FollowRequest> sentFollowRequests) {
        this.sentFollowRequests = sentFollowRequests;
    }

    public ArrayList<Chat> getChatsWithBots() {
        return chatsWithBots;
    }

    public void setChatsWithBots(ArrayList<Chat> chatsWithBots) {
        this.chatsWithBots = chatsWithBots;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public ArrayList<FollowRequest> getReceivedFollowRequests() {
        return receivedFollowRequests;
    }

    public void setReceivedFollowRequests(ArrayList<FollowRequest> receivedFollowRequests) {
        this.receivedFollowRequests = receivedFollowRequests;
    }

    public ArrayList<SystemMassage> getSystemMassages() {
        return systemMassages;
    }

    public void setSystemMassages(ArrayList<SystemMassage> systemMassages) {
        this.systemMassages = systemMassages;
    }

    public ArrayList<Tag> getUsed_tags() {
        return used_tags;
    }

    public void setUsed_tags(ArrayList<Tag> used_tags) {
        this.used_tags = used_tags;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public boolean isBirthdate_visibility() {
        return birthdate_visibility;
    }

    public void setBirthdate_visibility(boolean birthdate_visibility) {
        this.birthdate_visibility = birthdate_visibility;
    }

    public boolean isPrivate() {
        return Private;
    }

    public void setPrivate(boolean aPrivate) {
        Private = aPrivate;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isEverybodyCanSeeLastseen() {
        return everybodyCanSeeLastseen;
    }

    public boolean isGameBotFollower() {
        return gameBotFollower;
    }

    public void setGameBotFollower(boolean gameBotFollower) {
        this.gameBotFollower = gameBotFollower;
    }

    public void setEverybodyCanSeeLastseen(boolean everybodyCanSeeLastseen) {
        this.everybodyCanSeeLastseen = everybodyCanSeeLastseen;
    }

    public boolean isNobodyCanSeeLastseen() {
        return nobodyCanSeeLastseen;
    }

    public void setNobodyCanSeeLastseen(boolean nobodyCanSeeLastseen) {
        this.nobodyCanSeeLastseen = nobodyCanSeeLastseen;
    }

    public boolean isQuestionBotFollower() {
        return questionBotFollower;
    }

    public void setQuestionBotFollower(boolean questionBotFollower) {
        this.questionBotFollower = questionBotFollower;
    }

    public boolean isCalculatorBotFollower() {
        return calculatorBotFollower;
    }

    public void setCalculatorBotFollower(boolean calculatorBotFollower) {
        this.calculatorBotFollower = calculatorBotFollower;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void followThisFromRequest(User userToFollow) throws IOException {
        if(this.getFollowingsId().contains(userToFollow.getId())){return;}
        this.getFollowingsId().add(userToFollow.getId());
        userToFollow.getFollowersId().add(this.getId());
        SystemMassage systemMassage = new SystemMassage(this, userToFollow, this.getUsername()+" started following you");
        userToFollow.getSystemMassages().add(systemMassage);
    }

    public void addSystemMassage(SystemMassage systemMassage) throws IOException {
        this.getSystemMassages().add(systemMassage);
    }

    public static UserInfoServerEvent generateUserInfoEvent(User user){
        return new UserInfoServerEvent(
                user.getId(),
                user.getFollowersId().size(),
                user.getProfileImagePath(),
                user.getFollowingsId().size(),
                user.getName().getText(),
                user.getLastname().getText(),
                user.getUsername().getText(),
                user.getBio().getText(),
                user.getLastseen().toString(),
                user.isPrivate(),
                false);
    }

    public static ArrayList<UserInfoServerEvent> generateUserInfoEventFromUserList(ArrayList<User> userArrayList){
        ArrayList<UserInfoServerEvent> infoAraayList = new ArrayList<>();
        for (User user : userArrayList){
            infoAraayList.add(generateUserInfoEvent(user));
        }

        return infoAraayList;
    }

    public String generateBirthdateAsString(){
        if (birthdate == null){
            return "Null";
        }
        return birthdate.toString();
    }

    public static SendUserInfoForPP generateUserInfoForPP(User user){
        return new SendUserInfoForPP(
                user.getName().getText(),
                user.getLastname().getText(),
                user.getUsername().getText(),
                user.generateBirthdateAsString(),
                user.getEmail().getText(),
                user.getPhonenumber().toString(),
                user.getBio().getText(),
                user.getProfileImagePath()
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "\nId=" + Id +
                "\n, name=" + name +
                "\n, lastname=" + lastname +
                "\n, username=" + username +
                "\n, previous_username=" + previous_username +
                "\n, password=" + password +
                "\n, birthdate=" + birthdate +
                "\n, email=" + email +
                "\n, phonenumber=" + phonenumber +
                "\n, bio=" + bio +
                "\n, lastseen=" + lastseen +
                "\n, profileImagePath='" + profileImagePath + '\'' +
                "\n, active=" + active +
                "\n, savedmassages=" + savedmassages +
                "\n, tweets=" + tweets +
                "\n, retweets=" + retweets +
                "\n, LikedTweets=" + LikedTweets +
                "\n, receivedTweets=" + receivedTweets +
                "\n, followingsId=" + followingsId +
                "\n, followersId=" + followersId +
                "\n, blockedUsersId=" + blockedUsersId +
                "\n, muted_usersId=" + muted_usersId +
                "\n, sentFollowRequests=" + sentFollowRequests +
                "\n, receivedFollowRequests=" + receivedFollowRequests +
                "\n, systemMassages=" + systemMassages +
                "\n, used_tags=" + used_tags +
                "\n, chats=" + chats +
                "\n, groups=" + groups +
                "\n, birthdate_visibility=" + birthdate_visibility +
                "\n, Private=" + Private +
                "\n, disabled=" + disabled +
                "\n, deleted=" + deleted +
                "\n, everybodyCanSeeLastseen=" + everybodyCanSeeLastseen +
                "\n, nobodyCanSeeLastseen=" + nobodyCanSeeLastseen +
                "\n}";
    }
}
