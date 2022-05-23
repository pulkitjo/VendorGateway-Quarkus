package com.deliveryhero.gateway.rest.models.dps;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
public class DpsRequest {
  @JsonProperty("vendor_codes")
  private final Set<String> vendorCodes;
  @JsonProperty("global_entity_id")
  private final String globalEntityId;
  private final String brand;
  private final String country;
  @JsonProperty("request_id")
  private final String requestId;
  @JsonProperty("customer_id")
  private final Optional<String> customerId;
  @JsonProperty("dps_session_id")
  private final Optional<String> dpsSessionId;
  @JsonProperty("longitude")
  private final Optional<Double> longitude;
  @JsonProperty("latitude")
  private final Optional<Double> latitude;

  @JsonIgnore
  private final String trafficType;

  @JsonIgnore
  private final Optional<String> authorization;
}
