package com.deliveryhero.gateway.persistence;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "minimal_vendor_tbl")
@NamedQueries({@NamedQuery(name = "MinimalVendor.getBybid", query = "from MinimalVendor where bid = ?1")})
public class MinimalVendor extends PanacheEntityBase {

    @Id
    public String bid;


    public String topic;

    public String brand;

    public String country;

    public Date timestamp;

    public String version;

    public String payload;

    public Date processed_at;

    public String protobuf;


    public static List<MinimalVendor> getVendorByBID(Set<String> bids){
        List<MinimalVendor> res=null;
        try(Stream<MinimalVendor> mvs =MinimalVendor.streamAll()){
        res = mvs.filter(mv -> bids.contains(mv.bid)).collect(Collectors.toList());

        }

        return res;

    }





}
