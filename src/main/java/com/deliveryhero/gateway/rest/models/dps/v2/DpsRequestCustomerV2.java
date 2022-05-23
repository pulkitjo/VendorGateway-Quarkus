package com.deliveryhero.gateway.rest.models.dps.v2;


import com.deliveryhero.gateway.rest.models.dps.DpsRequestCustomer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter
public class DpsRequestCustomerV2 extends DpsRequestCustomer {
  @JsonProperty("session")
  private DpsRequestCustomerSession session;

  @JsonProperty("user_id")
  private String userId;


  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DpsRequestCustomerSession {
    @JsonProperty("id")
    private String id;

    @JsonProperty("timestamp")
    private long timestamp;
  }



}
