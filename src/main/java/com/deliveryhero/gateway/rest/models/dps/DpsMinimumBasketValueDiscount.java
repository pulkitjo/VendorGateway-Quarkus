package com.deliveryhero.gateway.rest.models.dps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DpsMinimumBasketValueDiscount {
  String type;
  Double basketValueThreshold;
  Double deliveryFeeTotal;
  Double discount;
}
