package models.messaging;

import controller.IDGenerator;
import controller.MainController;
import models.other.hyperlink.HyperLink;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private int id;
    private String name;
    private String token;
    private ArrayList<Integer> usersIds;
    private ArrayList<String> membersUsernames;
    private ArrayList<ChatMassage> chatMassages;

    public Group(String name) {
        this.id = IDGenerator.generateAnId(this);
        this.name = name;
        this.token = MainController.generateRandomString();
        this.usersIds = new ArrayList<>();
        this.membersUsernames = new ArrayList<>();
        this.chatMassages = new ArrayList<>();
    }

    public Group() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getUsersIds() {
        return usersIds;
    }

    public ArrayList<String> getMembersUsernames() {
        return membersUsernames;
    }

    public ArrayList<ChatMassage> getChatMassages() {
        return chatMassages;
    }

    public String getToken() {
        return token;
    }

    public void sendMassage(int senderId, String text, String imagePath, HyperLink hyperLink){
        ChatMassage chatMassage = new ChatMassage(senderId, text, imagePath, hyperLink);
        chatMassage.setChatMessageStatus(ChatMessageStatus.RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET);
        this.chatMassages.add(chatMassage);
    }

    public void addMember(int memberId, String memberUsername){
        usersIds.add(memberId);
        membersUsernames.add(memberUsername);
    }

    public void removeMember(int memberId, String memberUsername){
        for (int i = 0; i < usersIds.size(); i++) {
            if (usersIds.get(i) == memberId){
                usersIds.remove(i);
                break;
            }
        }
        membersUsernames.remove(memberUsername);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsersIds(ArrayList<Integer> usersIds) {
        this.usersIds = usersIds;
    }

    public void setMembersUsernames(ArrayList<String> membersUsernames) {
        this.membersUsernames = membersUsernames;
    }

    public void setChatMassages(ArrayList<ChatMassage> chatMassages) {
        this.chatMassages = chatMassages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
