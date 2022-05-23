package com.deliveryhero.gateway.rest.models.availability;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GeoFilterType {
  @JsonProperty("location")
  LOCATION,
  @JsonProperty("area_id")
  AREA_ID,
  @JsonProperty("city_id")
  CITY_ID,
  @JsonProperty("radius")
  RADIUS
}
