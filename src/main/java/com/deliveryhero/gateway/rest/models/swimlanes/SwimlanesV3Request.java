package com.deliveryhero.gateway.rest.models.swimlanes;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

/**
 * SwimlanesV3Request
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter


public class SwimlanesV3Request   {

  @JsonProperty("config")
  private String config;

  @JsonProperty("customer_id")
  private String customerId;

  @JsonProperty("gid")
  private String gid;

  @JsonProperty("language_code")
  private String languageCode;

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("longitude")
  private Double longitude;

  @JsonProperty("vendors")
  private List<Vendor> vendors = null;

}

