package com.deliveryhero.gateway.rest.models.listing;


import com.deliveryhero.gateway.rest.models.availability.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter
public class AvailableVendorsResponse {

@JsonProperty("data")
Data data;

}
