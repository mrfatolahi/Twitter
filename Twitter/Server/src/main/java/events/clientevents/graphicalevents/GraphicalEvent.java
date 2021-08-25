package events.clientevents.graphicalevents;

import events.clientevents.main.ClientEvent;

import java.time.LocalDateTime;

public abstract class GraphicalEvent extends ClientEvent {
    private final LocalDateTime createTime;

    public GraphicalEvent() {
        this.createTime = LocalDateTime.now();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
