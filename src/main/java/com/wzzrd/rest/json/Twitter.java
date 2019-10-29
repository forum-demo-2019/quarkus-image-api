package com.wzzrd.rest.json;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name="twitter")
public class Twitter extends PanacheEntity {

    public Long id;
    public BigInteger userid;
    public String username;
    public String screenname;
    public String url;
    public String filename;
    public LocalDate timestamp;
    public byte[] thumbnail;

    public static Twitter findByFilename (String composite_filename){
        String[] filename = composite_filename.split("_");
        return find("filename", filename[1]).firstResult();
    }

}
