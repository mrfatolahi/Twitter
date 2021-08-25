package saversandloaders.database.models.dbmessagingmodels;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class DBChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;
    private int user1Id;
    private int user2Id;
    private String user1Username;
    private String user2Username;
    private int user1NewMassages;
    private int user2NewMassages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DBChatMessage> chatMassages = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    public String getUser1Username() {
        return user1Username;
    }

    public void setUser1Username(String user1Username) {
        this.user1Username = user1Username;
    }

    public String getUser2Username() {
        return user2Username;
    }

    public void setUser2Username(String user2Username) {
        this.user2Username = user2Username;
    }

    public int getUser1NewMassages() {
        return user1NewMassages;
    }

    public void setUser1NewMassages(int user1NewMassages) {
        this.user1NewMassages = user1NewMassages;
    }

    public int getUser2NewMassages() {
        return user2NewMassages;
    }

    public void setUser2NewMassages(int user2NewMassages) {
        this.user2NewMassages = user2NewMassages;
    }

    public List<DBChatMessage> getChatMassages() {
        return chatMassages;
    }

    public void setChatMassages(List<DBChatMessage> chatMassages) {
        this.chatMassages = chatMassages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBChat dbChat = (DBChat) o;
        return getId() == dbChat.getId() && getUser1Id() == dbChat.getUser1Id() && getUser2Id() == dbChat.getUser2Id() && getUser1NewMassages() == dbChat.getUser1NewMassages() && getUser2NewMassages() == dbChat.getUser2NewMassages() && Objects.equals(getToken(), dbChat.getToken()) && Objects.equals(getUser1Username(), dbChat.getUser1Username()) && Objects.equals(getUser2Username(), dbChat.getUser2Username()) && Objects.equals(getChatMassages(), dbChat.getChatMassages());
    }
}
