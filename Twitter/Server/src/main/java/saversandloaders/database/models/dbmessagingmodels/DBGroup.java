package saversandloaders.database.models.dbmessagingmodels;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class DBGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String token;
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> usersIds = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> membersUsernames = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBGroupMessage> groupMessages = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsersIds(List<Integer> usersIds) {
        this.usersIds = usersIds;
    }

    public void setMembersUsernames(List<String> membersUsernames) {
        this.membersUsernames = membersUsernames;
    }

    public void setGroupMessages(List<DBGroupMessage> groupMessages) {
        this.groupMessages = groupMessages;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public List<Integer> getUsersIds() {
        return usersIds;
    }

    public List<String> getMembersUsernames() {
        return membersUsernames;
    }

    public List<DBGroupMessage> getGroupMessages() {
        return groupMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBGroup dbGroup = (DBGroup) o;
        return getId() == dbGroup.getId() && Objects.equals(getName(), dbGroup.getName()) && Objects.equals(getToken(), dbGroup.getToken()) && Objects.equals(getUsersIds(), dbGroup.getUsersIds()) && Objects.equals(getMembersUsernames(), dbGroup.getMembersUsernames()) && Objects.equals(getGroupMessages(), dbGroup.getGroupMessages());
    }
}
