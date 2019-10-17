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

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.File;

@Path("/api")
public class ImageResource {

    @ConfigProperty(name = "image-server.dirName")
    String dirName;

    @GET
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    public List all_images() throws IOException {
        List<String> resultlist = null;
        try (Stream<java.nio.file.Path> walk = Files.walk(Paths.get(dirName))) {
            resultlist = walk.filter(Files::isRegularFile).filter(f -> ! f.endsWith(".gitkeep"))
                .map(x -> "/api/image/" + x.getName(x.getNameCount() -1)
                    .toString()).collect(Collectors.toList());
            resultlist.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultlist;
    }

    @GET
    @Path("/image/{name}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response get_image(@PathParam("name") String name) {
        File file = new File(dirName + "/" + name);
        Response.ResponseBuilder builder;

        System.out.printf("Starting to send file %s...\n", name);
        if (file.exists()) {
            System.out.println("Called image/" + name + " which is OK, 200.");
            builder = Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" );
        } else {
            System.out.println("Tried opening " + dirName + "/" + name + " which does not exist.");
            System.out.println("Passing back a 404 NOT FOUND error.");
            builder = Response.status(Status.NOT_FOUND);
        }
        return builder.build();
    }
}
