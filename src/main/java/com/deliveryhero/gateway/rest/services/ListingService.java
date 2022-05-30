package com.deliveryhero.gateway.rest.services;

import com.deliveryhero.gateway.persistence.MinimalVendor;
import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import com.deliveryhero.gateway.rest.models.listing.AvailableVendorsResponse;
import com.deliveryhero.gateway.rest.models.listing.MinimalVendorResponse;
import com.deliveryhero.gateway.rest.services.clients.ListingClient;
import com.deliveryhero.gateway.rest.services.clients.filters.QueryParams;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequestScoped
public class ListingService extends AbstractService {



    @RestClient
    ListingClient listingClient;

    @Inject
    QueryParams queryParams;

    Set<MinimalVendor> mv=null;

    Logger logger = LoggerFactory.getLogger(ListingService.class);


    @CircuitBreaker
    public Uni<MinimalVendorResponse> getMinimalVendors(AvailabilityResponse availabilityResponse){
        long startTime =System.nanoTime();
        MinimalVendorResponse response =new MinimalVendorResponse();
        mapper = JsonMapper.builder().findAndAddModules().build();

        try {
            if(mv==null)
            mv = Set.of(mapper.readValue(getClass().getResourceAsStream("/minimal_vendor_tbl.json"), MinimalVendor[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

       Set<String> availabilitySet =availabilityResponse.getVendors().stream().map(v -> v.getId()).collect(Collectors.toSet());
        response.setMv(
                mv.stream().filter(e -> availabilitySet.contains(e.bid)).collect(Collectors.toList()));
        logger.info("Minimal Vendor time : " + TimeUnit.MILLISECONDS.convert(System.nanoTime()-startTime, TimeUnit.NANOSECONDS));
        return Uni.createFrom().item(response);



       // return Uni.createFrom().item(minimalVendorResponse);
    }


    public Uni<AvailableVendorsResponse> getVendorsFromListing(MultivaluedMap<String, String> uriQueryParameters){
        queryParams= new QueryParams(uriQueryParameters.entrySet().stream().
               collect(Collectors.toMap(Map.Entry::getKey,e->e.getValue().get(0))));
        return listingClient.getAvailableVendorsV1(queryParams);



    }

}
