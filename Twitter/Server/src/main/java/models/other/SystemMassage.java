package models.other;


import models.time.MDateTime;
import models.user.User;

import java.io.Serializable;

public class SystemMassage implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private int senderId;
    private int receiverId;
    private String text;
    private MDateTime sendtime;

    public SystemMassage() {
    }

    public SystemMassage(User sender, User receiver, String text) {
        this.senderId = sender.getId();
        this.receiverId = receiver.getId();
        this.text = text;
        this.sendtime = MDateTime.now();
        receiver.getSystemMassages().add(this);
    }

    public void Show(){
        System.out.println(text);
        System.out.println("\n"+sendtime);
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSendtime(MDateTime sendtime) {
        this.sendtime = sendtime;
    }

    public MDateTime getSendtime() {
        return sendtime;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text+"       "+sendtime;
    }
}
