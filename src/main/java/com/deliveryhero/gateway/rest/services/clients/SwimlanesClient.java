package com.deliveryhero.gateway.rest.services.clients;
import com.deliveryhero.gateway.rest.models.swimlanes.SwimlanesV3Request;
import com.deliveryhero.gateway.rest.models.swimlanes.SwimlanesV3Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.concurrent.CompletionStage;



@RegisterRestClient(configKey = "v3")
public interface SwimlanesClient {


    @Path("/swimlanes")
    @POST
    @CircuitBreaker(requestVolumeThreshold = 20)
    CompletionStage<SwimlanesV3Response> getSwimlanes(SwimlanesV3Request request);




}
