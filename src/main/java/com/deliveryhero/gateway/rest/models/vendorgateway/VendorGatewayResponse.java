package com.deliveryhero.gateway.rest.models.vendorgateway;

import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import com.deliveryhero.gateway.rest.models.dps.DpsResponse;
import com.deliveryhero.gateway.rest.models.listing.MinimalVendorResponse;

@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter
public class VendorGatewayResponse {

    AvailabilityResponse availabilityResponse;

    DpsResponse dpsResponse;

    MinimalVendorResponse minimalVendorResponse;

}
