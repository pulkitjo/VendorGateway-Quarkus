package com.deliveryhero.gateway.rest.models.dps.v1;


import com.deliveryhero.gateway.rest.models.dps.DpsRequestCustomer;

public class DpsRequestCustomerV1 extends DpsRequestCustomer {
  private final String sessionId;

  public DpsRequestCustomerV1(String id, Double latitude, Double longitude, String sessionId) {
    super(id, latitude, longitude);
    this.sessionId = sessionId;
  }

  public String getSessionId() {
    return sessionId;
  }
}
