package models.messaging;

import controller.MainController;
import models.other.hyperlink.HyperLink;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private String token;
    private int user1Id;
    private int user2Id;
    private String user1Username;
    private String user2Username;
    private ArrayList<ChatMassage> chatMassages;
    private int user1NewMassages;
    private int user2NewMassages;

    public Chat() {
    }

    public Chat(int user1Id, int user2Id, String user1Username, String user2Username) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.token = MainController.generateRandomString();
        this.user1Username = user1Username;
        this.user2Username = user2Username;
        this.chatMassages = new ArrayList<ChatMassage>();
        this.user1NewMassages = 0;
        this.user2NewMassages = 0;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public String getUser1Username() {
        return user1Username;
    }

    public String getUser2Username() {
        return user2Username;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
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

    public ArrayList<ChatMassage> getChatMassages() {
        return chatMassages;
    }

    public void setChatMassages(ArrayList<ChatMassage> chatMassages) {
        this.chatMassages = chatMassages;
    }

    public String getToken() {
        return token;
    }

    public void sendMassage(int senderId, String text, String imagePath, HyperLink hyperLink){
        if(senderId == user1Id){user2NewMassages++;}else
        if(senderId == user2Id){user1NewMassages++;}
        ChatMassage chatMassage = new ChatMassage(senderId, text, imagePath, hyperLink);
        this.chatMassages.add(chatMassage);
    }

}
