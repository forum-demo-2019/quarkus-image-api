package com.wzzrd.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ImageResourceTest {

    @Test
    void testAllImagesEndpoint() {
        given()
          .when().get("/api/images")
          .then()
             .statusCode(200);
    }

}
