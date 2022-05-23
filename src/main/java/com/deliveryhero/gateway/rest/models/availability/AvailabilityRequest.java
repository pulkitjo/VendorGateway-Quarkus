package com.deliveryhero.gateway.rest.models.availability;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AvailabilityRequest {

  protected String requestId = "empty-request";
  @JsonProperty("traffic_type")
  protected String trafficType;

}
