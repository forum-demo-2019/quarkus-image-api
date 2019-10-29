package com.wzzrd.rest.json;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/api/v2")
public class TwitterResource {

    @ConfigProperty(name = "image-server.hostName")
    String hostName;

    @GET
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> all_images() {
        Stream<Twitter> Tweets = Twitter.streamAll();
        return Tweets
                .map(t -> hostName + "/api/v2/image/" + t.filename)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/image/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response get(@PathParam("filename") String filename) {
        Response.ResponseBuilder builder;
        Twitter tweet = Twitter.findByFilename(filename);
        builder = Response.ok(tweet.thumbnail, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + tweet.filename + "\"" );
        return builder.build();
    }

}
