package com.wzzrd.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ImageResourceTest {

    @Test
    public void testAllImagesEndpoint() {
        given()
          .when().get("/api/all_images")
          .then()
             .statusCode(200);
    }

}
