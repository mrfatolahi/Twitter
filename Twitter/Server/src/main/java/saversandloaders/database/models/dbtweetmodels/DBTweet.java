package saversandloaders.database.models.dbtweetmodels;

import saversandloaders.database.models.dbtimemodels.DBMDateTime;
import saversandloaders.database.models.dbtweetmodels.retweetmodels.DBRetweet;
import saversandloaders.database.models.dbusermodels.DBUser;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class DBTweet {
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
    private int type;
    @Embedded
    private DBMDateTime DateAndTimeCreated;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBTag> dbTags = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBComment> dbComments = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBLike> dbLikes = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBRetweet> dbRetweets = new ArrayList<>();

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

    public List<DBComment> getDbComments() {
        return dbComments;
    }

    public void setDbComments(List<DBComment> dbComments) {
        this.dbComments = dbComments;
    }

    public List<DBLike> getDbLikes() {
        return dbLikes;
    }

    public void setDbLikes(List<DBLike> dbLikes) {
        this.dbLikes = dbLikes;
    }

    public List<DBRetweet> getDbRetweets() {
        return dbRetweets;
    }

    public void setDbRetweets(List<DBRetweet> dbRetweets) {
        this.dbRetweets = dbRetweets;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static DBTweet generateNullFillerDBTweet(){
        DBTweet dbTweet = new DBTweet();
        dbTweet.setUserId(-100);
        dbTweet.setText("-100");
        dbTweet.setImagePath("-100");
        dbTweet.setUserUsername("-100");
        dbTweet.setUserProfileImagePath("-100");
        dbTweet.setTimesReported(-100);
        DBMDateTime dbmDateTime = new DBMDateTime();
        dbmDateTime.setYear(-100);
        dbmDateTime.setMonth(-100);
        dbmDateTime.setDay(-100);
        dbmDateTime.setHour(-100);
        dbmDateTime.setMinute(-100);
        dbTweet.setDateAndTimeCreated(dbmDateTime);

        return dbTweet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBTweet dbTweet = (DBTweet) o;
        return getId() == dbTweet.getId() && getUserId() == dbTweet.getUserId() && getTimesReported() == dbTweet.getTimesReported() && Objects.equals(getText(), dbTweet.getText()) && Objects.equals(getImagePath(), dbTweet.getImagePath()) && Objects.equals(getUserUsername(), dbTweet.getUserUsername()) && Objects.equals(getUserProfileImagePath(), dbTweet.getUserProfileImagePath());
    }
}
