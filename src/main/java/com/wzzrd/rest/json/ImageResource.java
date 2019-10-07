package com.wzzrd.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Path("/api")
public class ImageResource {

    @GET
    @Path("/all_images")
    @Produces(MediaType.TEXT_PLAIN)
    public String all_images() throws IOException{
        String dirName = "/home/mburgerh/work/java/quarkus-img-api/src/main/resources/images";
        try (Stream<java.nio.file.Path> paths = Files.walk(Paths.get(dirName))) {
            paths.filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException ex) {
                System.out.println("problem! image can't be loaded!");
        }
        return "all_images";
    }

    @GET
    @Path("/image")
    @Produces(MediaType.TEXT_PLAIN)
    public String get_image() {
        return "just one";
    }

}

