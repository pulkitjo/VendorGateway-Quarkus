package com.deliveryhero.gateway.rest.models.swimlanes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@AllArgsConstructor
@Data
public class SwimlanesRequest {
    SwimlanesV3Request requestBody;
    @JsonIgnore
    private final String requestId;

    @JsonIgnore
    private final String trafficType;
}
