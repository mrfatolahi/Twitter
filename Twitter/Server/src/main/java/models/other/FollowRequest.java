package models.other;

import models.time.MDateTime;
import models.user.User;
import saversandloaders.loaders.UserLoader;

import java.io.IOException;
import java.io.Serializable;

public class FollowRequest implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private int senderId;
    private int receiverId;
    private String receiverUsername;
    private String text;
    private MDateTime sendtime;
    private boolean accepted;

    public FollowRequest() {
    }

    public FollowRequest(User sender, User receiver, String text, String receiverUsername) {
        this.receiverUsername = receiverUsername;
        this.senderId = sender.getId();
        this.receiverId = receiver.getId();
        this.text = text;
        this.sendtime = MDateTime.now();
        accepted = false;
    }

    public void Show() throws IOException {
        UserLoader userLoader = new UserLoader();
        System.out.println(text);
        System.out.println("\n"+sendtime);
    }

    public String getText() {
        return text;
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

    public void setText(String text) {
        this.text = text;
    }

    public MDateTime getSendtime() {
        return sendtime;
    }

    public void setSendtime(MDateTime sendtime) {
        this.sendtime = sendtime;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public boolean Equals(FollowRequest followRequest){
        if(!this.sendtime.toString().equals(followRequest.sendtime.toString())){return false;}
        if(this.senderId != followRequest.senderId){return false;}
        if(this.receiverId != followRequest.receiverId){return false;}
        return this.text.equals(followRequest.text);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
