package com.deliveryhero.gateway.rest.models.availability;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AvailabilityVendorsRequest extends AvailabilityRequest {

  private String brand;
  private String country;
  @JsonProperty("geo_filter")
  private Optional<GeoFilterRequest> geoFilter;
  @JsonProperty("delivery_type")
  private String deliveryType;

  private Optional<Integer> limit;
  private Optional<Integer> offset;

  private Set<String> vendorIds;
  private Set<String> cuisineIds;
  private Optional<String> customerType;
  private Set<String> verticalTypes;

  private Optional<String> orderTime;

  private boolean explain;

  @JsonIgnore
  private String requestId;

  @JsonIgnore
  private String trafficType;

}
