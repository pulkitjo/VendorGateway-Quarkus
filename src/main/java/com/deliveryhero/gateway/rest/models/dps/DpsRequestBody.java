package com.deliveryhero.gateway.rest.models.dps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@Data
public class DpsRequestBody {

  @JsonProperty("vendors")
  private Set<DpsRequestVendor> vendors;

  @JsonProperty("customer")
  private DpsRequestCustomer customer;

  @JsonProperty("promised_delivery_time")
  private Optional<String> promisedDeliveryTime = Optional.empty();

  public DpsRequestBody(Set<DpsRequestVendor> vendors, DpsRequestCustomer customer) {
    this.vendors = vendors;
    this.customer = customer;
  }

}
