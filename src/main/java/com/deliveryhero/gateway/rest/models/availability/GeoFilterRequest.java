package com.deliveryhero.gateway.rest.models.availability;

import lombok.Data;
import org.apache.commons.collections4.SetUtils;

import java.util.Optional;
import java.util.Set;

@Data
public class GeoFilterRequest {

  public static final double DEFAULT_RADIUS = 5000;
  private final GeoFilterType type;
  private final Optional<String> areaId;
  private final Set<String> cityIds;
  private final Optional<Double> lat;
  private final Optional<Double> lon;
  private final Optional<Double> radius;

  private GeoFilterRequest(
    GeoFilterType type,
    Optional<String> areaId,
    Set<String> cityIds,
    Optional<Double> lat,
    Optional<Double> lon,
    Optional<Double> radius
  ) {
    this.type = type;
    this.areaId = areaId;
    this.cityIds = cityIds;
    this.lat = lat;
    this.lon = lon;
    this.radius = radius;
  }

  public static GeoFilterRequest location(Double lat, Double lon) {
    return new GeoFilterRequest(
      GeoFilterType.LOCATION,
      Optional.empty(),
      Set.of(),
      Optional.of(lat),
      Optional.of(lon),
      Optional.empty());
  }

  public static GeoFilterRequest radius(Double lat, Double lon, Double radius) {
    return new GeoFilterRequest(
      GeoFilterType.RADIUS,
      Optional.empty(),
      Set.of(),
      Optional.of(lat),
      Optional.of(lon),
      Optional.of(radius));
  }

  public static GeoFilterRequest areaId(String areaId) {
    return new GeoFilterRequest(
      GeoFilterType.AREA_ID,
      Optional.empty(),
      Set.of(areaId),
      Optional.empty(),
      Optional.empty(),
      Optional.empty());
  }

  public static GeoFilterRequest cityIds(Set<String> cityIds, Optional<Double> latitude, Optional<Double> longitude) {
    return new GeoFilterRequest(
      GeoFilterType.CITY_ID,
      Optional.empty(),
      SetUtils.emptyIfNull(cityIds),
      latitude,
      longitude,
      Optional.empty());
  }

}
