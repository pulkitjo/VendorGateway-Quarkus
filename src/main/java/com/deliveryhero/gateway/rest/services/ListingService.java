package com.deliveryhero.gateway.rest.services;

import com.deliveryhero.gateway.persistence.MinimalVendor;
import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import com.deliveryhero.gateway.rest.models.listing.MinimalVendorResponse;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListingService extends AbstractService {


    @Inject
    MinimalVendorResponse minimalVendorResponse;

    public Uni<MinimalVendorResponse> getMinimalVendors(AvailabilityResponse availabilityResponse){



        minimalVendorResponse.setMv(
        MinimalVendor.getVendorByBID(availabilityResponse.getVendors().stream().
                map(vendor -> vendor.getId()).collect(Collectors.toSet())));


        return Uni.createFrom().item(minimalVendorResponse);
    }

}
