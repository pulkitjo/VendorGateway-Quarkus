package com.deliveryhero.gateway.rest.services;

import com.deliveryhero.gateway.persistence.MinimalVendor;
import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import com.deliveryhero.gateway.rest.models.listing.AvailableVendorsResponse;
import com.deliveryhero.gateway.rest.models.listing.MinimalVendorResponse;
import com.deliveryhero.gateway.rest.services.clients.ListingClient;
import com.deliveryhero.gateway.rest.services.clients.filters.QueryParams;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequestScoped
public class ListingService extends AbstractService {


    @Inject
    MinimalVendorResponse minimalVendorResponse;



    @RestClient
    ListingClient listingClient;

    @Inject
    QueryParams queryParams;


    @CircuitBreaker
    public Uni<MinimalVendorResponse> getMinimalVendors(AvailabilityResponse availabilityResponse){



        minimalVendorResponse.setMv(
                MinimalVendor.getVendorByBID(availabilityResponse.getVendors().stream().
                map(vendor -> vendor.getId()).collect(Collectors.toSet())));


        return Uni.createFrom().item(minimalVendorResponse);
    }


    public Uni<AvailableVendorsResponse> getVendorsFromListing(MultivaluedMap<String, String> uriQueryParameters){
        queryParams= new QueryParams(uriQueryParameters.entrySet().stream().
               collect(Collectors.toMap(Map.Entry::getKey,e->e.getValue().get(0))));
        return listingClient.getAvailableVendorsV1(queryParams);



    }

}
