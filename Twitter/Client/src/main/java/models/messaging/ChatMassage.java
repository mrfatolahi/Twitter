package models.messaging;


import javafx.scene.control.Hyperlink;
import models.other.hyperlink.HyperLink;
import models.time.MDateTime;

import java.io.Serializable;

public class ChatMassage implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private int userId;
    private ChatMessageStatus chatMessageStatus;
    private MDateTime timeCreated;
    private String text;
    private String imagePath;
    private HyperLink hyperlink;

    public ChatMassage() {
    }

    public ChatMassage(int userId, String text, String imagePath, HyperLink hyperlink) {
        this.userId = userId;
        this.timeCreated = MDateTime.now();
        this.chatMessageStatus = ChatMessageStatus.HAVE_NOT_RECORDED_IN_SERVER;
        this.text = text;
        this.imagePath = imagePath;
        this.hyperlink = hyperlink;
    }

    public ChatMessageStatus getChatMessageStatus() {
        return chatMessageStatus;
    }

    public void setChatMessageStatus(ChatMessageStatus chatMessageStatus) {
        this.chatMessageStatus = chatMessageStatus;
    }

    public int getUserId() {
        return userId;
    }

    public MDateTime getTimeCreated() {
        return timeCreated;
    }

    public String getText() {
        return text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTimeCreated(MDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public HyperLink getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(HyperLink hyperlink) {
        this.hyperlink = hyperlink;
    }
}
