package com.deliveryhero.gateway.rest.services.clients.filters;

import javax.enterprise.context.RequestScoped;
import java.util.Map;

@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter

@RequestScoped
public class QueryParams {


    Map<String,String> params;
}
