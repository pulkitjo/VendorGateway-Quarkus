package com.deliveryhero.gateway.rest.services.clients;

import com.deliveryhero.gateway.rest.models.availability.AvailabilityRequest;
import com.deliveryhero.gateway.rest.models.availability.response.AvailabilityResponse;
import io.smallrye.mutiny.Uni;
import io.vertx.core.buffer.Buffer;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.net.http.HttpResponse;

@RegisterRestClient(configKey="availability")
public interface AvailabilityClient {

    @POST
    @Path("/vendors")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @CircuitBreaker()
    //@Timeout()
    @ClientHeaderParam(name = "Accept", value = "application/octet-stream")
    Uni<InputStream> getAvailableVendors(AvailabilityRequest request);
}
