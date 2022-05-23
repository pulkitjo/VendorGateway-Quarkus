package com.deliveryhero.gateway.rest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import java.util.Map;

public abstract class AbstractService {

    @Inject
    @ConfigProperty(name = "brandCountryConfig.map")
    Map<String,String> brandCountryMap;

    ObjectMapper mapper;

}
