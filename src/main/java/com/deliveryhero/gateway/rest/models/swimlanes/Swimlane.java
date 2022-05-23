package com.deliveryhero.gateway.rest.models.swimlanes;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

/**
 * Swimlane
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Setter @lombok.Getter


public class Swimlane   {

  @JsonProperty("template")
  private String template;

  @JsonProperty("sorting_strategy")
  private String sortingStrategy;

  @JsonProperty("traces")
  private List<String> traces = null;

  @JsonProperty("filters")
  private List<String> filters = null;

  @JsonProperty("products")
  private List<String> products = null;

  @JsonProperty("layout")
  private String layout;

  @JsonProperty("content_type")
  private String contentType;

  @JsonProperty("swimlane_type")
  private String swimlaneType;

  @JsonProperty("custom_strategy")
  private String customStrategy;

  @JsonProperty("min_items")
  private Integer minItems;

  @JsonProperty("ranking")
  private Integer ranking;

  @JsonProperty("id")
  private String id;

  @JsonProperty("headline")
  private String headline;

  @JsonProperty("is_premium")
  private Boolean isPremium;

  @JsonProperty("ncr_pricing_model")
  private String ncrPricingModel;

  @JsonProperty("ncr_token")
  private String ncrToken;

  @JsonProperty("sub_headline")
  private String subHeadline;

  @JsonProperty("vendors")
  private List<Vendor> vendors = null;

  @JsonProperty("max_items")
  private Integer maxItems;

}

