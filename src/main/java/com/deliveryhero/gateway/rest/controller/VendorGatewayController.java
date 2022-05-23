package com.deliveryhero.gateway.rest.controller;

import com.deliveryhero.gateway.rest.models.swimlanes.SwimlanesV3Request;
import com.deliveryhero.gateway.rest.models.swimlanes.SwimlanesV3Response;
import com.deliveryhero.gateway.rest.models.vendorgateway.VendorGatewayResponse;
import com.deliveryhero.gateway.rest.services.AvailabilityService;
import com.deliveryhero.gateway.rest.services.DPSService;
import com.deliveryhero.gateway.rest.services.clients.SwimlanesClient;
import com.deliveryhero.gateway.rest.services.GatewayService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@Path("/gateway")
public class VendorGatewayController {

    @RestClient
    SwimlanesClient swimlanesService;

    @Inject
    DPSService dpsService;

    @Inject
    GatewayService vendorService;

    @Inject
    AvailabilityService availabilityService;

    @Context UriInfo uriInfo;

    /*@POST
    @Path("/dps")
    public CompletionStage<DpsResponse> getDPS(){

     return dpsService.getDPS(request);
    }*/

    @POST
    @Path("/swimlanes")
    public  CompletionStage<SwimlanesV3Response> getSwimlanes(SwimlanesV3Request request){
        return swimlanesService.getSwimlanes(request);

    }
    /*@GET
    @Path("/dps")
    public Uni<DpsResponse> getDPS() throws ExecutionException, InterruptedException {
        return vendorService.getVendors(uriInfo.getQueryParameters());

    }*/

    @GET
    @Path("/vendors")
    public Uni<VendorGatewayResponse> getVendors() throws ExecutionException, InterruptedException {

       // return availabilityService.getAvailableVendors(uriInfo.getQueryParameters());
        return vendorService.getRLPResponse(uriInfo.getQueryParameters());

    }


}
