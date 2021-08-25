package events.serverevents.luser.explorerpage;

public class UserInfoServerEvent extends ExplorerPageServerEvent {
    private static final long serialVersionUID = 1234567L;
    private final int ownerId;
    private final int numberOfFollowers;
    private final String profileImagePath;
    private final int numberOfFollowings;
    private final String name;
    private final String lastName;
    private final String username;
    private final String bio;
    private final String lastseen;
    private final boolean privacyStatus;
    private final boolean bot;

    public UserInfoServerEvent(
            int ownerId, int numberOfFollowers, String profileImagePath,
            int numberOfFollowings, String name, String lastName,
            String username, String bio, String lastseen, boolean privacyStatus,
            boolean bot) {
        this.ownerId = ownerId;
        this.numberOfFollowers = numberOfFollowers;
        this.profileImagePath = profileImagePath;
        this.numberOfFollowings = numberOfFollowings;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.bio = bio;
        this.lastseen = lastseen;
        this.privacyStatus = privacyStatus;
        this.bot = bot;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public int getNumberOfFollowings() {
        return numberOfFollowings;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getLastseen() {
        return lastseen;
    }

    public boolean getPrivacyStatus() {
        return privacyStatus;
    }

    public boolean isPrivacyStatus() {
        return privacyStatus;
    }

    public Object getUser(){
        if (ownerId == -1){
            return null;
        }else {
            return new Object();
        }
    }
}
