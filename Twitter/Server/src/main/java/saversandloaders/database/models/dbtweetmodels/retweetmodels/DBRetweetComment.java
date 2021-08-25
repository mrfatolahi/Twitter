package saversandloaders.database.models.dbtweetmodels.retweetmodels;

import saversandloaders.database.models.dbtimemodels.DBMDateTime;
import saversandloaders.database.models.dbtweetmodels.DBComment;
import saversandloaders.database.models.dbtweetmodels.DBLike;
import saversandloaders.database.models.dbtweetmodels.DBTag;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class DBRetweetComment {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
