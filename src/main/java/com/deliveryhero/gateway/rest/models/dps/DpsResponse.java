package com.deliveryhero.gateway.rest.models.dps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DpsResponse {

  public static final DpsResponse EMPTY = new DpsResponse();
  public static final DpsResponse BRAND_COUNTRY_NOT_CONFIGURED = new DpsResponse();
  public static final DpsResponse UNSUPPORTED_SESSION_ID_FORMAT = new DpsResponse();
  public static final DpsResponse NO_SESSION_DPS_SESSION_ID = new DpsResponse();

  List<DpsResponseVendor> vendors = List.of();
  DpsResponseCustomer customer;

}
