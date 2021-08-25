package saversandloaders.database.models.dbothermodels;

import saversandloaders.database.models.dbtimemodels.DBMDateTime;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DBSystemMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int senderId;
    private int receiverId;
    @Column(length = 10000)
    private String text;
    @Embedded
    private DBMDateTime sendtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DBMDateTime getSendtime() {
        return sendtime;
    }

    public void setSendtime(DBMDateTime sendtime) {
        this.sendtime = sendtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBSystemMessage that = (DBSystemMessage) o;
        return getId() == that.getId() && getSenderId() == that.getSenderId() && getReceiverId() == that.getReceiverId() && Objects.equals(getText(), that.getText()) && Objects.equals(getSendtime(), that.getSendtime());
    }
}
