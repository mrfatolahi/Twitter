package saversandloaders.database.models.dbtweetmodels.retweetmodels;

import saversandloaders.database.models.dbtimemodels.DBMDateTime;
import saversandloaders.database.models.dbtweetmodels.DBLike;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import saversandloaders.database.models.dbtweetmodels.DBTweet;
import saversandloaders.database.models.dbusermodels.DBUser;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class DBRetweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    @Column(length = 10000)
    private String text;
    @Column(length = 10000)
    private String imagePath;
    private String userUsername;
    @Column(length = 10000)
    private String userProfileImagePath;
    private int timesReported;
    @Embedded
    private DBMDateTime DateAndTimeCreated;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBTag> dbTags = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBRetweetComment> dbRetweetComments = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBLike> dbLikes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBRetweet> dbRetweets = new ArrayList<>();

    @ManyToOne
    private DBTweet dbTweet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserProfileImagePath() {
        return userProfileImagePath;
    }

    public void setUserProfileImagePath(String userProfileImagePath) {
        this.userProfileImagePath = userProfileImagePath;
    }

    public int getTimesReported() {
        return timesReported;
    }

    public void setTimesReported(int timesReported) {
        this.timesReported = timesReported;
    }

    public DBMDateTime getDateAndTimeCreated() {
        return DateAndTimeCreated;
    }

    public void setDateAndTimeCreated(DBMDateTime dateAndTimeCreated) {
        DateAndTimeCreated = dateAndTimeCreated;
    }

    public List<DBTag> getDbTags() {
        return dbTags;
    }

    public void setDbTags(List<DBTag> dbTags) {
        this.dbTags = dbTags;
    }

    public List<DBRetweetComment> getDbRetweetComments() {
        return dbRetweetComments;
    }

    public void setDbRetweetComments(List<DBRetweetComment> dbRetweetComments) {
        this.dbRetweetComments = dbRetweetComments;
    }

    public List<DBLike> getDbLikes() {
        return dbLikes;
    }

    public void setDbLikes(List<DBLike> dbRetweetLikes) {
        this.dbLikes = dbRetweetLikes;
    }

    public DBTweet getDbTweet() {
        return dbTweet;
    }

    public void setDbTweet(DBTweet dbTweet) {
        this.dbTweet = dbTweet;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<DBRetweet> getDbRetweets() {
        return dbRetweets;
    }

    public void setDbRetweets(List<DBRetweet> dbRetweets) {
        this.dbRetweets = dbRetweets;
    }

    public static DBRetweet generateNullFillerDBReTweet(){
        DBRetweet dbRetweet = new DBRetweet();
        dbRetweet.setUserId(-100);
        dbRetweet.setText("-100");
        dbRetweet.setImagePath("-100");
        dbRetweet.setUserUsername("-100");
        dbRetweet.setUserProfileImagePath("-100");
        dbRetweet.setTimesReported(-100);
        DBMDateTime dbmDateTime = new DBMDateTime();
        dbmDateTime.setYear(-100);
        dbmDateTime.setMonth(-100);
        dbmDateTime.setDay(-100);
        dbmDateTime.setHour(-100);
        dbmDateTime.setMinute(-100);
        dbRetweet.setDateAndTimeCreated(dbmDateTime);

        return dbRetweet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBRetweet dbRetweet = (DBRetweet) o;
        return getId() == dbRetweet.getId() && getUserId() == dbRetweet.getUserId() && getTimesReported() == dbRetweet.getTimesReported() && Objects.equals(getText(), dbRetweet.getText()) && Objects.equals(getImagePath(), dbRetweet.getImagePath()) && Objects.equals(getUserUsername(), dbRetweet.getUserUsername()) && Objects.equals(getUserProfileImagePath(), dbRetweet.getUserProfileImagePath());
    }
}
