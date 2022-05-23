package com.deliveryhero.gateway.rest.models.availability.response.vendorBody;


import com.deliveryhero.gateway.rest.models.availability.response.UnavailabilityReason;
import com.deliveryhero.gateway.rest.models.availability.response.VendorStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class MissingVendorData implements VendorDataResponse {
    String id;
    VendorStatus status = VendorStatus.MISSING;
    @JsonProperty("missing_reason")
    UnavailabilityReason missingReason;

    public MissingVendorData(String id, UnavailabilityReason missingReason) {
        this.id = id;
        this.missingReason = missingReason;
    }
}
