package com.deliveryhero.gateway.rest.models.swimlanes;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

/**
 * Meta
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter


public class Meta   {

  @JsonProperty("config_name")
  private String configName;

  @JsonProperty("took")
  private Integer took;

  @JsonProperty("traces")
  private List<String> traces = null;


}

