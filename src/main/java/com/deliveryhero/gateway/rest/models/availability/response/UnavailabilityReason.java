package com.deliveryhero.gateway.rest.models.availability.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UnavailabilityReason {

    @JsonProperty("unknown_id")
    UNKNOWN_ID,
    @JsonProperty("no_schedule")
    NO_SCHEDULE,
    @JsonProperty("location_not_in_da")
    NO_DELIVERY_AREA,
    @JsonProperty("expedition_type_disabled")
    EXPEDITION_TYPE_DISABLED,
    @JsonProperty("inactive")
    INACTIVE,
    @JsonProperty("filtered_by_vertical_type")
    OTHER_VERTICAL_TYPE,
    @JsonProperty("filtered_by_customer_type")
    OTHER_CUSTOMER_TYPE,
    @JsonProperty("filtered_by_chain_id")
    OTHER_CHAIN_ID,
    @JsonProperty("filtered_by_in_dining")
    OTHER_IN_DINING,
    @JsonProperty("filtered_by_cuisine")
    OTHER_CUISINE,
    @JsonProperty("filtered_by_city")
    OTHER_CITY

}
