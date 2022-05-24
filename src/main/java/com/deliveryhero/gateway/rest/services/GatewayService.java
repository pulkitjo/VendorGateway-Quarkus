package com.deliveryhero.gateway.rest.services;

import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import com.deliveryhero.gateway.rest.models.availability.response.vendorBody.VendorData;
import com.deliveryhero.gateway.rest.models.dps.DpsResponse;
import com.deliveryhero.gateway.rest.models.listing.MinimalVendorResponse;
import com.deliveryhero.gateway.rest.models.vendorgateway.VendorGatewayResponse;
import com.deliveryhero.gateway.rest.services.clients.ListingClient;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequestScoped
public class GatewayService {

    @RestClient
    ListingClient listingClient;

    @Inject
    AvailabilityService availabilityService ;

    @Inject
    DPSService dpsService;

    @Inject
    ListingService listingService;

    @Inject
    VendorGatewayResponse gatewayResponse;

    Logger logger = LoggerFactory.getLogger(GatewayService.class);


    public Uni<VendorGatewayResponse> getRLPResponse(MultivaluedMap<String, String> uriQueryParameters){

        long tempTime =System.nanoTime();
        return availabilityService.getAvailableVendors(uriQueryParameters).
                onItem().transformToUni(
                        item -> {
                            long availabilityTime =System.nanoTime() - tempTime;
                            logger.info("Availability call took : " + TimeUnit.MILLISECONDS.convert(availabilityTime, TimeUnit.NANOSECONDS));
            Uni<DpsResponse> dps = dpsService.getDPS(item, uriQueryParameters.get("country").get(0),
                    uriQueryParameters.get("longitude").get(0), uriQueryParameters.get("latitude").get(0));
            Uni<MinimalVendorResponse> mvs = listingService.getMinimalVendors(item);
            return Uni.combine().all().unis(dps,mvs).asTuple().onItem().transformToUni(t -> {
              gatewayResponse.setDpsResponse(t.getItem1());
             gatewayResponse.setMinimalVendorResponse(t.getItem2());
              gatewayResponse.setAvailabilityResponse(item);
              return Uni.createFrom().item(gatewayResponse);
            });

        });

    }

    public Uni<VendorGatewayResponse> getRLPResponseUsingListing(MultivaluedMap<String, String> uriQueryParameters){


        return listingService.getVendorsFromListing(uriQueryParameters).onItem().transformToUni(item -> {
            AvailabilityResponse availabilityResponse = new AvailabilityResponse();
            availabilityResponse.setVendors(item.getData().getCodes().stream().map(e -> {
                VendorData v = new VendorData();
                v.setId(e);
                return v;
            }).collect(Collectors.toList()));

            Uni<DpsResponse> dps = dpsService.getDPS(availabilityResponse, uriQueryParameters.get("country").get(0),
                    uriQueryParameters.get("longitude").get(0), uriQueryParameters.get("latitude").get(0));
            Uni<MinimalVendorResponse> mvs = listingService.getMinimalVendors(availabilityResponse);
            return Uni.combine().all().unis(dps,mvs).asTuple().onItem().transformToUni(t -> {
                VendorGatewayResponse gatewayResponse= new VendorGatewayResponse();
                gatewayResponse.setDpsResponse(t.getItem1());
                gatewayResponse.setMinimalVendorResponse(t.getItem2());
                gatewayResponse.setAvailabilityResponse(availabilityResponse);
                return Uni.createFrom().item(gatewayResponse);
            });


        });


    }

}
