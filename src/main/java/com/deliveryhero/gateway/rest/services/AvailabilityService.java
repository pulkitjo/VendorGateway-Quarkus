package com.deliveryhero.gateway.rest.services;

import com.deliveryhero.gateway.rest.models.availability.AvailabilityRequest;
import com.deliveryhero.gateway.rest.models.availability.AvailabilityVendorsRequest;
import com.deliveryhero.gateway.rest.models.availability.ExpeditionType;
import com.deliveryhero.gateway.rest.models.availability.GeoFilterRequest;
import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import com.deliveryhero.gateway.rest.services.clients.AvailabilityClient;
import com.deliveryhero.gateway.rest.services.clients.DPSClient;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class AvailabilityService extends AbstractService{

    @RestClient
    AvailabilityClient availabilityClient;




    public Uni<AvailabilityResponse> getAvailableVendors(MultivaluedMap<String,String> uriQueryParameters) {
        mapper = JsonMapper.builder().findAndAddModules().build();
        AvailabilityRequest req = formAvailabilityRequest(uriQueryParameters);

         return availabilityClient.getAvailableVendors(req).onItem().transform(item -> {
             AvailabilityResponse response = null;
                  try {
                        response = mapper.readValue(item.readAllBytes(),AvailabilityResponse.class);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                    return response;

         }
         );
    }

    private AvailabilityRequest formAvailabilityRequest(MultivaluedMap<String,String> uriQueryParameters) {
        HashMap<String,String> params = new HashMap();
        uriQueryParameters.entrySet().stream().forEach(k -> {
            params.put(k.getKey(),k.getValue().get(0));
        });
        AvailabilityVendorsRequest request = new AvailabilityVendorsRequest();

        request.setBrand(brandCountryMap.get(params.get("country")));
        request.setCountry(params.get("country"));
        GeoFilterRequest geoFilter = GeoFilterRequest.location(Double.parseDouble(params.get("latitude")),
                Double.parseDouble( params.get("longitude")));
        request.setDeliveryType(ExpeditionType.DELIVERY.getValue());
        request.setGeoFilter(Optional.of(geoFilter));
        return request;



    }
}
