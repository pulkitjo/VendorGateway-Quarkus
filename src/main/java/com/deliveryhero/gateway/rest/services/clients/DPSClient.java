package com.deliveryhero.gateway.rest.services.clients;

import com.deliveryhero.gateway.rest.models.dps.DpsRequest;
import com.deliveryhero.gateway.rest.models.dps.DpsRequestBody;
import com.deliveryhero.gateway.rest.models.dps.DpsResponse;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.concurrent.CompletionStage;

@Path("/api")
@RegisterRestClient(configKey="dps")
public interface DPSClient {

    @POST
    @Path("/v1/fees/{id}")
    @CircuitBreaker(requestVolumeThreshold = 20)
    CompletionStage<DpsResponse> getDPSv1(@PathParam("id")String geid, DpsRequestBody request);

    @POST
    @Path("/v2/fees/{id}")
    @CircuitBreaker(requestVolumeThreshold = 20)
    Uni<DpsResponse> getDPSv2(@PathParam("id")String geid, DpsRequestBody request);


}
