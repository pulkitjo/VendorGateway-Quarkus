package com.deliveryhero.gateway.rest.services.clients;

import com.deliveryhero.gateway.rest.models.listing.AvailableVendorsResponse;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashMap;


@Path("/listing")
@RegisterRestClient(configKey="listing")
public interface ListingClient {



    @GET
    @ClientHeaderParam(name = "x-disco-client-id", value = "web")
    @Path("/api/v1/pandora/vendors/available")
    Uni<AvailableVendorsResponse> getAvailableVendorsV1(HashMap<String,String> queryParameters);

}
