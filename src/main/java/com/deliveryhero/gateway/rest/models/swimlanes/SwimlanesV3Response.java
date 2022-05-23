package com.deliveryhero.gateway.rest.models.swimlanes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * SwimlanesV3Response
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter

public class SwimlanesV3Response   {

  @JsonProperty("meta")
  private Meta meta;

  @JsonProperty("swimlanes")
  private List<Swimlane> swimlanes = null;

  @JsonProperty("request_id")
  private String requestId;

  @JsonProperty("status")
  private Integer status;

  public static final SwimlanesV3Response EMPTY = new SwimlanesV3Response();


}

