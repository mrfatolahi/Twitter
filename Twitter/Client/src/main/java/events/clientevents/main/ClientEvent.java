package events.clientevents.main;


import java.io.Serializable;

public abstract class ClientEvent implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
