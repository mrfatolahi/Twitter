package events.serverevents.nluser;

public class LogInResultEvent extends NLUserServerEvent {
    private int id;
    private final LogInResults logInResults;

    public LogInResultEvent(LogInResults logInResults, int id) {
        this.id = id;
        this.logInResults = logInResults;
    }

    public LogInResultEvent(LogInResults logInResults) {
        this.id = -1;
        this.logInResults = logInResults;
    }

    public LogInResults getLogInResult() {
        return logInResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
