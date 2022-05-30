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

    private static List<MinimalVendor> mvs;

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

        if(mvs==null){
            try(Stream<MinimalVendor> stream =MinimalVendor.streamAll()){
                mvs = stream.collect(Collectors.toList());
            }

        }

        res = mvs.stream().filter(mv -> bids.contains(mv.bid)).collect(Collectors.toList());


        return res;

    }





}
