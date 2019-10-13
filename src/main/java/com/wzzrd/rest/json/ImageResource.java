package com.wzzrd.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.PathParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;
import java.util.*;

import java.io.File;

@Path("/api")
public class ImageResource {

    @GET
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    public List all_images() throws IOException{
        String dirName = "/home/mburgerh/work/java/quarkus-image-api/src/main/resources/images";
        try (Stream<java.nio.file.Path> walk = Files.walk(Paths.get(dirName))) {
            List<String> result = walk.filter(Files::isRegularFile).filter(f -> ! f.endsWith(".gitkeep"))
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
    @Path("/image/{name}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response get_image(@PathParam("name") String name) {
        String dirName = "/home/mburgerh/work/java/quarkus-image-api/src/main/resources/images/";
        File file = new File(dirName + name);
        System.out.print("Starting process...");
        if (file.exists()) {
            System.out.print("Called image/" + name + " which is OK, 200.");
            return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) //optional
                .build();
        } else {
            System.out.print("Called image/" + name + " which is NOT_FOUND, 404.");
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
