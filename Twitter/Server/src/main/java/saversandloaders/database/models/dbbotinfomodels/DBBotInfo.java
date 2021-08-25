package saversandloaders.database.models.dbbotinfomodels;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DBBotInfo {

    @Id
    private int id;
    private String username;
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> followersId = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int type) {
        this.id = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getFollowersId() {
        return followersId;
    }

    public void setFollowersId(List<Integer> followersId) {
        this.followersId = followersId;
    }
}
