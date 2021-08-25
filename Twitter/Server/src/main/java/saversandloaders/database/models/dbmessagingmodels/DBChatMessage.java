package saversandloaders.database.models.dbmessagingmodels;

import models.other.hyperlink.HyperLinkType;
import saversandloaders.database.models.dbtimemodels.DBMDateTime;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DBChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int chatMessageStatus;
    @Embedded
    private DBMDateTime timeCreated;
    @Column(length = 10000)
    private String text;
    @Column(length = 10000)
    private String imagePath;

    private String hyperLinkText;
    private String hyperLinkToken;
    private String hyperLinkType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatMessageStatus() {
        return chatMessageStatus;
    }

    public void setChatMessageStatus(int chatMessageStatus) {
        this.chatMessageStatus = chatMessageStatus;
    }

    public DBMDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(DBMDateTime timeCreated) {
        this.timeCreated = timeCreated;
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

    public String getHyperLinkText() {
        return hyperLinkText;
    }

    public void setHyperLinkText(String hyperLinkText) {
        this.hyperLinkText = hyperLinkText;
    }

    public String getHyperLinkToken() {
        return hyperLinkToken;
    }

    public void setHyperLinkToken(String hyperLinkToken) {
        this.hyperLinkToken = hyperLinkToken;
    }

    public String getHyperLinkType() {
        return hyperLinkType;
    }

    public void setHyperLinkType(String hyperLinkType) {
        this.hyperLinkType = hyperLinkType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBChatMessage that = (DBChatMessage) o;
        return getId() == that.getId() && getUserId() == that.getUserId() && getChatMessageStatus() == that.getChatMessageStatus() && Objects.equals(getTimeCreated(), that.getTimeCreated()) && Objects.equals(getText(), that.getText()) && Objects.equals(getImagePath(), that.getImagePath());
    }
}
