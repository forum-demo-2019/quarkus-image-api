package com.wzzrd.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/api/v2")
public class TwitterResource {

    @GET
    @Path("/get/names")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> all_images() {
        Stream<Twitter> Tweets = Twitter.streamAll();
        return Tweets
                .map(t -> t.screenname)
                .collect(Collectors.toList());
    }

}
