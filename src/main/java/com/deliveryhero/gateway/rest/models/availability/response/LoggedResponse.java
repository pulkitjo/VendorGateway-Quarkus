package com.deliveryhero.gateway.rest.models.availability.response;


import lombok.Value;

@Value(staticConstructor = "of")
public class LoggedResponse <T> {
    T response;
    ResponseLog log;
}
