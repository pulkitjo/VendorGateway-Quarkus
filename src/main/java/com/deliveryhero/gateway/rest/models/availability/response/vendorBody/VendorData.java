package com.deliveryhero.gateway.rest.models.availability.response.vendorBody;


import com.deliveryhero.gateway.rest.models.availability.response.VendorStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;

@Builder
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class VendorData implements VendorDataResponse {
    private String id;
    private Double lat;
    private Double lon;
    private Double  distance;
    private VendorStatus status;
    @JsonProperty("status_ignore_shrinking")
    private VendorStatus statusIgnoreShrinking;
    private Set<String> events;
    @JsonProperty("minimum_delivery_fee")
    private Double minimumDeliveryFee;
    @JsonProperty("delivery_fee_type")
    private String deliveryFeeType;
    @JsonProperty("minimum_order_amount")
    private Double minimumOrderAmount;
    @JsonProperty("delivery_time")
    private Double deliveryTime;
    @JsonProperty("available_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime availableAt;
    @JsonProperty("vertical_type")
    private String verticalType;
    @JsonProperty("delivery_types")
    private Set<String> deliveryTypes;
    @JsonProperty("chain_id")
    private String chainId;

    private double delay;
    @JsonProperty("delivery_allowed")
    private boolean deliveryAllowed;
    @JsonProperty("pickup_allowed")
    private boolean pickupAllowed;
    @JsonProperty("in_dining")
    private boolean inDining;
}
