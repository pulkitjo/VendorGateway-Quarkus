package com.deliveryhero.gateway.rest.models.availability.response;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDataResponse {
    private String id;
    private String action;

    public static EventDataResponse lockdownEvent(String id) {
        return new EventDataResponse(id, EventAction.LOCKDOWN.name());
    }
}
