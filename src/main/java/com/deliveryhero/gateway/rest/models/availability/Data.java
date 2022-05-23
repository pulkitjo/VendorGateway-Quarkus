package com.deliveryhero.gateway.rest.models.availability;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter
public class Data {
    @JsonProperty("codes")
    Set<String> codes;
    
}
