package com.deliveryhero.gateway.persistence;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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

@Entity
@Cacheable
@Table(name = "minimal_vendor_tbl")
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

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public static List<MinimalVendor> getVendorByBID(Set<String> bids){
        List<MinimalVendor> res=null;
        try(Stream<MinimalVendor> mvs =MinimalVendor.streamAll()){
        res = mvs.filter(mv -> bids.contains(mv.bid)).collect(Collectors.toList());

        }

        return res;

    }





}
