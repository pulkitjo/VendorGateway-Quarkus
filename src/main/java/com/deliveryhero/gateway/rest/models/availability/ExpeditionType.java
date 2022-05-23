package com.deliveryhero.gateway.rest.models.availability;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum ExpeditionType {
  @JsonProperty("delivery")
  DELIVERY("delivery"),
  @JsonProperty("pickup")
  PICKUP("pickup"),
  @JsonProperty("dine_in")
  DINE_IN("dine_in");

  @Getter
  private final String value;

  ExpeditionType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

}
