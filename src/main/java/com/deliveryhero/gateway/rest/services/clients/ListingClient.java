package com.deliveryhero.gateway.rest.services.clients;

import com.deliveryhero.gateway.rest.models.listing.AvailableVendorsResponse;

import com.deliveryhero.gateway.rest.services.clients.filters.QueryParams;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/listing")
@RegisterRestClient(configKey="listing")
public interface ListingClient {



    @GET
    @ClientHeaderParam(name = "x-disco-client-id", value = "web")
    @Path("/api/v1/pandora/vendors/available")
    @CircuitBreaker(requestVolumeThreshold = 20)
    Uni<AvailableVendorsResponse> getAvailableVendorsV1(QueryParams queryParameters);

}
