package events.clientevents.graphicalevents.notloggeduser;

public class LogInPageEvent extends NLUserGraphicalEvent {
    private final String Username;
    private final String Password;

    public LogInPageEvent(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
