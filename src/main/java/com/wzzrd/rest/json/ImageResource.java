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
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList all_images() throws IOException{
        String dirName = "/home/mburgerh/work/java/quarkus-img-api/src/main/resources/images";
        ArrayList imageArrayList2 = new ArrayList();
        try (Stream<java.nio.file.Path> paths = Files.walk(Paths.get(dirName))) {
            System.out.println(paths);
            List<java.nio.file.Path> imageList = paths.collect(Collectors.toList());
            System.out.println(imageList);
            ArrayList<java.nio.file.Path> imageArrayList = new ArrayList<java.nio.file.Path>(imageList);
            System.out.println(imageArrayList);
            return imageArrayList;
        } catch (IOException ex) {
                System.out.println("problem! image can't be loaded!");
        }
        return imageArrayList2;

    }

    @GET
    @Path("/image")
    @Produces(MediaType.TEXT_PLAIN)
    public String get_image() {
        return "just one";
    }

}

