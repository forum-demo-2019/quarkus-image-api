package com.wzzrd.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class TwitterResourceTest {

    @Test
    void testAllImagesEndpoint() {
        given()
          .when().get("/api/v2/images")
          .then()
             .statusCode(200);
    }

}
