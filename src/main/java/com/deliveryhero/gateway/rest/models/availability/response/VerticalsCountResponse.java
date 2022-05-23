package com.deliveryhero.gateway.rest.models.availability.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class VerticalsCountResponse {

    private Map<String, Integer> verticalsCount;

}
