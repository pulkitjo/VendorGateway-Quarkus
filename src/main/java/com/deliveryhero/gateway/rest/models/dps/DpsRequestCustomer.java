package com.deliveryhero.gateway.rest.models.dps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class DpsRequestCustomer {

  @JsonProperty("id")
  private String id;

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("longitude")
  private Double longitude;

  public DpsRequestCustomer(String id, Double latitude, Double longitude) {
    this.id = id;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
