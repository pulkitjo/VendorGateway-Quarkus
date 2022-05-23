package com.deliveryhero.gateway.rest.models.availability.response;

public enum EventAction {

    CLOSED("close", true),
    SHRINK("shrink", true),
    DELAY("delay", false),
    LOCKDOWN("lockdown", true);

    private final String name;
    private final boolean closing;

    EventAction(String name, boolean closing) {
        this.name = name;
        this.closing = closing;
    }

    public boolean isClosing() {
        return closing;
    }

    public static EventAction byName(final String name) {
        for (EventAction e : EventAction.values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        throw new RuntimeException(String.format("Event type not supported: %s \n;", name));
    }

}
