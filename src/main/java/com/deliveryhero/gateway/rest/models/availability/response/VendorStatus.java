package com.deliveryhero.gateway.rest.models.availability.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VendorStatus {

    @JsonProperty("open")
    OPEN,
    @JsonProperty("closed")
    CLOSED,
    @JsonProperty("closed_tmp")
    TEMPORARY_CLOSED,
    @JsonProperty("missing")
    MISSING;


    public boolean isOpen() {
        return this == VendorStatus.OPEN;
    }

}
