package com.deliveryhero.gateway.rest.models.swimlanes;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

/**
 * Vendor
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter


public class Vendor   {

  @JsonProperty("vendor_id")
  private String vendorId;


}

