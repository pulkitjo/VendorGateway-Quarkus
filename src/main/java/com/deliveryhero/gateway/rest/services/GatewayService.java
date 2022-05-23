package com.deliveryhero.gateway.rest.services;

import com.deliveryhero.gateway.rest.models.dps.DpsResponse;
import com.deliveryhero.gateway.rest.models.listing.MinimalVendorResponse;
import com.deliveryhero.gateway.rest.models.vendorgateway.VendorGatewayResponse;
import com.deliveryhero.gateway.rest.services.clients.ListingClient;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class GatewayService {

    @RestClient
    ListingClient listingClient;

    @Inject
    AvailabilityService availabilityService ;

    @Inject
    DPSService dpsService;

    @Inject
    ListingService listingService;


    public Uni<VendorGatewayResponse> getRLPResponse(MultivaluedMap<String, String> uriQueryParameters){


        return availabilityService.getAvailableVendors(uriQueryParameters).
                onItem().transformToUni(
                        item -> {
            Uni<DpsResponse> dps = dpsService.getDPS(item, uriQueryParameters.get("country").get(0),
                    uriQueryParameters.get("longitude").get(0), uriQueryParameters.get("latitude").get(0));
            Uni<MinimalVendorResponse> mvs = listingService.getMinimalVendors(item);
            return Uni.combine().all().unis(dps,mvs).asTuple().onItem().transformToUni(t -> {
              VendorGatewayResponse gatewayResponse= new VendorGatewayResponse();
              gatewayResponse.setDpsResponse(t.getItem1());
             gatewayResponse.setMinimalVendorResponse(t.getItem2());
              gatewayResponse.setAvailabilityResponse(item);
              return Uni.createFrom().item(gatewayResponse);
            });

        });

    }

}
