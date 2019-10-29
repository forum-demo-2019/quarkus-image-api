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
    public LocalDate timestamp;
    public String thumbnail;

}