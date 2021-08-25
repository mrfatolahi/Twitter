package events.serverevents.main;

public class SendAuthTokenSE extends ServerEvent{
    private final String authToken;

    public SendAuthTokenSE(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}