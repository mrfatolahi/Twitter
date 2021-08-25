package events.clientevents.main;

public class LogInWithIdEvent extends ClientEvent{
    private final int id;

    public LogInWithIdEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
