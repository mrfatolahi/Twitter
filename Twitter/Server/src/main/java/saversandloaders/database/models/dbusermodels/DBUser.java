package saversandloaders.database.models.dbusermodels;

import saversandloaders.database.models.dbmessagingmodels.DBChat;
import saversandloaders.database.models.dbmessagingmodels.DBGroup;
import saversandloaders.database.models.dbothermodels.DBFollowRequest;
import saversandloaders.database.models.dbothermodels.DBRFollowRequest;
import saversandloaders.database.models.dbothermodels.DBSystemMessage;
import saversandloaders.database.models.dbtimemodels.DBMDateTime;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweet;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import saversandloaders.database.models.dbtweetmodels.DBTweet;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class DBUser {

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private int dbId;
    private int id;
    private String name;
    private String lastname;
    private String username;
    private String previosUsername;
    private String password;
    private String Email;
    private String phonenumber;
    @Column(length = 10000)
    private String bio;
    @Column(length = 10000)
    private String profileImagePath;
    private int birthdateDay;
    private int birthdateMonth;
    private int birthdateYear;
    private boolean active;
    private boolean birthdateVisible;
    private boolean Private;
    private boolean disabled;
    private boolean deleted;
    private boolean everybodyCanSeeLastseen;
    private boolean nobodyCanSeeLastseen;
    private boolean gameBotFollower;
    private boolean questionBotFollower;
    private boolean calculatorBotFollower;

    @Embedded
    private DBMDateTime lastseen;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBTweet> tweets = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBRetweet> retweets = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> followingsId = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> followersId = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> blockedUsersId = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> mutedUsersId = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBFollowRequest> sentFollowRequests = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBRFollowRequest> receivedFollowRequests = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBSystemMessage> systemMassages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBTag> usedTags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBChat> dbChats = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBGroup> dbGroups = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPreviosUsername() {
        return previosUsername;
    }

    public void setPreviosUsername(String previosUsername) {
        this.previosUsername = previosUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBirthdateVisible() {
        return birthdateVisible;
    }

    public void setBirthdateVisible(boolean birthdate_visibility) {
        this.birthdateVisible = birthdate_visibility;
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

    public void setEverybodyCanSeeLastseen(boolean everybodyCanSeeLastseen) {
        this.everybodyCanSeeLastseen = everybodyCanSeeLastseen;
    }

    public boolean isNobodyCanSeeLastseen() {
        return nobodyCanSeeLastseen;
    }

    public void setNobodyCanSeeLastseen(boolean nobodyCanSeeLastseen) {
        this.nobodyCanSeeLastseen = nobodyCanSeeLastseen;
    }

    public DBMDateTime getLastseen() {
        return lastseen;
    }

    public void setLastseen(DBMDateTime lastseen) {
        this.lastseen = lastseen;
    }

//    public DBMDate getBirthdate() {
//        return birthdate;
//    }
//
//    public void setBirthdate(DBMDate birthdate) {
//        this.birthdate = birthdate;
//    }
//

    public List<DBTweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<DBTweet> tweets) {
        this.tweets = tweets;
    }

    public List<DBRetweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(List<DBRetweet> retweets) {
        this.retweets = retweets;
    }

    public List<Integer> getFollowingsId() {
        return followingsId;
    }

    public void setFollowingsId(List<Integer> followingsId) {
        this.followingsId = followingsId;
    }

    public List<Integer> getFollowersId() {
        return followersId;
    }

    public void setFollowersId(List<Integer> followersId) {
        this.followersId = followersId;
    }

    public List<Integer> getBlockedUsersId() {
        return blockedUsersId;
    }

    public void setBlockedUsersId(List<Integer> blockedUsersId) {
        this.blockedUsersId = blockedUsersId;
    }

    public List<Integer> getMutedUsersId() {
        return mutedUsersId;
    }

    public void setMutedUsersId(List<Integer> muted_usersId) {
        this.mutedUsersId = muted_usersId;
    }

    public List<DBFollowRequest> getSentFollowRequests() {
        return sentFollowRequests;
    }

    public void setSentFollowRequests(List<DBFollowRequest> sentFollowRequests) {
        this.sentFollowRequests = sentFollowRequests;
    }

    public List<DBRFollowRequest> getReceivedFollowRequests() {
        return receivedFollowRequests;
    }

    public void setReceivedFollowRequests(List<DBRFollowRequest> receivedFollowRequests) {
        this.receivedFollowRequests = receivedFollowRequests;
    }

    public List<DBSystemMessage> getSystemMassages() {
        return systemMassages;
    }

    public void setSystemMassages(List<DBSystemMessage> systemMassages) {
        this.systemMassages = systemMassages;
    }

    public List<DBTag> getUsedTags() {
        return usedTags;
    }

    public void setUsedTags(List<DBTag> usedTags) {
        this.usedTags = usedTags;
    }

    public List<DBChat> getDbChats() {
        return dbChats;
    }

    public void setDbChats(List<DBChat> dbChats) {
        this.dbChats = dbChats;
    }

    public List<DBGroup> getDbGroups() {
        return dbGroups;
    }

    public void setDbGroups(List<DBGroup> dbGroups) {
        this.dbGroups = dbGroups;
    }

    public boolean isGameBotFollower() {
        return gameBotFollower;
    }

    public void setGameBotFollower(boolean gameBotFollower) {
        this.gameBotFollower = gameBotFollower;
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

    public int getBirthdateDay() {
        return birthdateDay;
    }

    public void setBirthdateDay(int birthdateDay) {
        this.birthdateDay = birthdateDay;
    }

    public int getBirthdateMonth() {
        return birthdateMonth;
    }

    public void setBirthdateMonth(int birthdateMonth) {
        this.birthdateMonth = birthdateMonth;
    }

    public int getBirthdateYear() {
        return birthdateYear;
    }

    public void setBirthdateYear(int birthdateYear) {
        this.birthdateYear = birthdateYear;
    }

    //    public int getDbId() {
//        return dbId;
//    }
//
//    public void setDbId(int dbId) {
//        this.dbId = dbId;
//    }

    @Override
    public String toString() {
        return "DBUser{" +
//                "  \ndbId=" + dbId +
                ", \nid=" + id +
                ", \nname='" + name + '\'' +
                ", \nlastname='" + lastname + '\'' +
                ", \nusername='" + username + '\'' +
                ", \npreviosUsername='" + previosUsername + '\'' +
                ", \npassword='" + password + '\'' +
                ", \nEmail='" + Email + '\'' +
                ", \nphonenumber='" + phonenumber + '\'' +
                ", \nbio='" + bio + '\'' +
                ", \nprofileImagePath='" + profileImagePath + '\'' +
                ", \nactive=" + active +
                ", \nbirthdate_visibility=" + birthdateVisible +
                ", \nPrivate=" + Private +
                ", \ndisabled=" + disabled +
                ", \ndeleted=" + deleted +
                ", \neverybodyCanSeeLastseen=" + everybodyCanSeeLastseen +
                ", \nnobodyCanSeeLastseen=" + nobodyCanSeeLastseen +
                ", \ngameBotFollower=" + gameBotFollower +
                ", \nquestionBotFollower=" + questionBotFollower +
                ", \ncalculatorBotFollower=" + calculatorBotFollower +
                ", \nlastseen=" + lastseen +
                ", \ntweets=" + tweets +
                ", \nretweets=" + retweets +
                ", \nfollowingsId=" + followingsId +
                ", \nfollowersId=" + followersId +
                ", \nblockedUsersId=" + blockedUsersId +
                ", \nmuted_usersId=" + mutedUsersId +
                ", \nsentFollowRequests=" + sentFollowRequests +
                ", \nreceivedFollowRequests=" + receivedFollowRequests +
                ", \nsystemMassages=" + systemMassages +
                ", \nusedTags=" + usedTags +
                ", \ndbChats=" + dbChats +
                ", \ndbGroups=" + dbGroups +
                "  \n}";
    }

    public void equalize(DBUser reference){

        this.setName(reference.getName());
        this.setLastname(reference.getLastname());
        this.setUsername(reference.getUsername());
        this.setPassword(reference.getPassword());
        this.setPreviosUsername(reference.getPreviosUsername());
        this.setEmail(reference.getEmail());
        this.setPhonenumber(reference.getPhonenumber());
        this.setBio(reference.getBio());
        this.setProfileImagePath(reference.getProfileImagePath());
        this.setActive(reference.isActive());
        this.setBirthdateVisible(reference.isBirthdateVisible());
        this.setPrivate(reference.isPrivate());
        this.setDisabled(reference.isDisabled());
        this.setDeleted(reference.isDeleted());
        this.setEverybodyCanSeeLastseen(reference.isEverybodyCanSeeLastseen());
        this.setNobodyCanSeeLastseen(reference.isNobodyCanSeeLastseen());
        this.setGameBotFollower(reference.isGameBotFollower());
        this.setQuestionBotFollower(reference.isQuestionBotFollower());
        this.setCalculatorBotFollower(reference.isCalculatorBotFollower());

        ///////////////////////////////////////////////////////////////

        this.setLastseen(reference.getLastseen());

        ///////////////////////////////////////////////////////////////

        this.setTweets(reference.getTweets());
        this.setRetweets(reference.getRetweets());

        ///////////////////////////////////////////////////////////////

        this.setFollowingsId(reference.getFollowingsId());
        this.setFollowersId(reference.getFollowersId());
        this.setBlockedUsersId(reference.getBlockedUsersId());
        this.setMutedUsersId(reference.getMutedUsersId());

        ///////////////////////////////////////////////////////////////

        this.setSentFollowRequests(reference.getSentFollowRequests());
        this.setReceivedFollowRequests(reference.getReceivedFollowRequests());
        this.setSystemMassages(reference.getSystemMassages());

        ///////////////////////////////////////////////////////////////

        this.setUsedTags(reference.getUsedTags());

        ///////////////////////////////////////////////////////////////

        this.setDbChats(reference.getDbChats());
        this.setDbGroups(reference.getDbGroups());

    }
}
