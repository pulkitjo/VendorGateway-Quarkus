package com.deliveryhero.gateway.rest.models.dps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryFee {
  Double total;
  Double travel_time;
  Double fleet_utilisation;
  StandardFee standardFee;
  DpsMinimumBasketValueDiscount minimum;
}
