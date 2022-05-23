package com.deliveryhero.gateway.rest.services.clients.filters;

import io.vertx.core.http.impl.headers.HeadersMultiMap;

import javax.inject.Inject;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
public class QueryParamFilter implements ClientRequestFilter {





    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (requestContext.getEntity() instanceof Map && requestContext.getMethod().equals(HttpMethod.GET)) {
            UriBuilder uriBuilder = UriBuilder.fromUri(requestContext.getUri());
            Map allParam = (Map)requestContext.getEntity();
            for (Object key : allParam.keySet()) {
                uriBuilder.queryParam(key.toString(), allParam.get(key));
            }
            /*uriBuilder.queryParam("country","sg");
            uriBuilder.queryParam("latitude","1.340400");
            uriBuilder.queryParam("longitude","103.709000");*/
            requestContext.setUri(uriBuilder.build());
            requestContext.setEntity(null);
        }
    }
}
