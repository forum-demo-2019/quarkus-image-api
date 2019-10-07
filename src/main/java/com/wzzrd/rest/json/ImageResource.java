package com.wzzrd.rest.json;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;
import java.util.*;

@Path("/api")
public class ImageResource {

    @GET
    @Path("/all_images")
    @Produces(MediaType.APPLICATION_JSON)
    public List all_images() throws IOException{
        String dirName = "/home/mburgerh/work/java/quarkus-img-api/src/main/resources/images";
        try (Stream<java.nio.file.Path> walk = Files.walk(Paths.get(dirName))) {
            List<String> result = walk.filter(Files::isRegularFile)
                .map(x -> "/api/image/" + x.getName(x.getNameCount() -1)
                    .toString()).collect(Collectors.toList());
            result.forEach(System.out::println);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        List empty = new ArrayList();
        return empty;
    }

    @GET
    @Path("/image")
    @Produces(MediaType.TEXT_PLAIN)
    public String get_image() {
        return "just one";
    }

}

