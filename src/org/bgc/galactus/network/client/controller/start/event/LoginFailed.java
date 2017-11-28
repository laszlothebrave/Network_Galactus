package org.bgc.galactus.network.client.controller.start.event;


import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class LoginFailed extends Event {

    public static final EventType<LoginFailed> LOGIN_FAILED =
            new EventType<>(Event.ANY, "LOGIN_FAILED");

    public LoginFailed() {
        super(LOGIN_FAILED);
    }

    public LoginFailed(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }

    public LoginFailed(Object source, EventTarget target) {
        super(source, target, LOGIN_FAILED);
    }
}

