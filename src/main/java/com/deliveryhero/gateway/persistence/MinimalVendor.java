package com.deliveryhero.gateway.persistence;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.EqualsAndHashCode;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MinimalVendor  {

    @EqualsAndHashCode.Include
    public String bid;


    public String topic;

    public String brand;

    public String country;

    public String timestamp;

    public String version;

    public String payload;

    public String processed_at;

    public String partition_key;

    public String protobuf;







}
