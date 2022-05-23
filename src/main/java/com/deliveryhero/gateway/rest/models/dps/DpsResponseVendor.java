package com.deliveryhero.gateway.rest.models.dps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DpsResponseVendor {

  String id;
  String currency;
  DeliveryFee deliveryFee;
  MinOrderValue minimumOrderValue;
  List<DpsPriceReason> priceReason;

}
