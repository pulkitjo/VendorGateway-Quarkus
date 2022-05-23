package com.deliveryhero.gateway.rest.models.listing;

import com.deliveryhero.gateway.persistence.MinimalVendor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter
public class MinimalVendorResponse {

    List<MinimalVendor> mv;
}
