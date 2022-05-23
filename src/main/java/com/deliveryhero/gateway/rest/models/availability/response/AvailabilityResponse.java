package com.deliveryhero.gateway.rest.models.availability.response;


import com.deliveryhero.gateway.rest.models.availability.response.vendorBody.VendorData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailabilityResponse {
    private int returned;
    private int open;
    private int closed;
    @JsonProperty("temporary_closed")
    private int temporaryClosed;
    private int missing;
    private List<String> events;
    @JsonProperty("event_data")
    private List<EventDataResponse> eventData;
    private List<VendorData> vendors;
    private int available;


}