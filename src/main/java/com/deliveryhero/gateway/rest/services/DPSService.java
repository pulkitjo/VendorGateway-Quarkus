package com.deliveryhero.gateway.rest.services;

import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import com.deliveryhero.gateway.rest.models.dps.DpsRequestBody;
import com.deliveryhero.gateway.rest.models.dps.DpsRequestCustomer;
import com.deliveryhero.gateway.rest.models.dps.DpsRequestVendor;
import com.deliveryhero.gateway.rest.models.dps.DpsResponse;
import com.deliveryhero.gateway.rest.models.dps.v2.DpsRequestCustomerV2;
import com.deliveryhero.gateway.rest.services.clients.DPSClient;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class DPSService extends AbstractService{

    @RestClient
    DPSClient dpsClient;



    public Uni<DpsResponse> getDPS(AvailabilityResponse availabilityResponse, String country, String lon, String lat) {

        return dpsClient.getDPSv2(brandCountryMap.get(country)+"_"+country,
                new DpsRequestBody(getVendorsFromAvailabilityResponse(availabilityResponse),formDPSRequestCustomer(lon,lat)));



    }

    private DpsRequestCustomer formDPSRequestCustomer(String lon, String lat) {
        DpsRequestCustomerV2 consumer= new DpsRequestCustomerV2();
        consumer.setLatitude(Double.parseDouble(lat));
        consumer.setLongitude(Double.parseDouble(lon));
        consumer.setId("");
        consumer.setUserId("");
        consumer.setSession(new DpsRequestCustomerV2.DpsRequestCustomerSession("",System.nanoTime()));
        return consumer;

    }

    private Set<DpsRequestVendor> getVendorsFromAvailabilityResponse(AvailabilityResponse availabilityResponse) {

        return availabilityResponse.getVendors().stream().map(vendor -> {return new DpsRequestVendor(vendor.getId());}).collect(Collectors.toSet());
    }

}
