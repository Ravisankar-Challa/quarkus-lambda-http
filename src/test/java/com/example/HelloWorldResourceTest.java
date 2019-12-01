package com.example;

import static com.example.util.Constants.APPLICATION_ERROR_JSON;
import static com.example.util.Constants.ERROR_CODE;
import static com.example.util.Constants.ERROR_MESSAGE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

@QuarkusTest
public class HelloWorldResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/hello")
          .then()
             .statusCode(200)
             .body(is("{\"hello\": \"world\"}"));
    }

    @Test
    public void should_return_400_for_get_request_with_invalid_name() {
        given().
               accept(MediaType.TEXT_PLAIN).
        when().
               get("/api/get/validate/86").
        then().
               log().all().
               statusCode(400).
               contentType(APPLICATION_ERROR_JSON).
               body(ERROR_CODE, equalTo(400)).
               body(ERROR_MESSAGE, allOf(containsString("Question Missing"),
                                         containsString("Version Missing"),
                                         containsString("Name format is incorrect")));
    }
	
    @Test
    public void should_return_400_for_get_request_with_invalid_version() {
        given().
               accept(MediaType.TEXT_PLAIN).
               header("version", "ABC").
        when().
               get("/api/get/validate/RAVI").
        then().
               log().all().
               statusCode(400).
               contentType(APPLICATION_ERROR_JSON).
               body(ERROR_CODE, equalTo(400)).
               body(ERROR_MESSAGE, allOf(containsString("Question Missing"),
                                         containsString("Version incorrect format")));
    }
	
    @Test
    public void should_return_400_for_get_request_with_invalid_question() {
        given().
               accept(MediaType.TEXT_PLAIN).
               header("version", "1.0").
               queryParam("question", "1111").
        when().
               get("/api/get/validate/RAVI").
        then().
               log().all().
               statusCode(400).
               contentType(APPLICATION_ERROR_JSON).
               body(ERROR_CODE, equalTo(400)).
               body(ERROR_MESSAGE, equalTo("Question format is incorrect"));
    }

    @Test
    public void should_return_200_for_valid_get_request() {
	    Response response = 
        given().
               accept(MediaType.TEXT_PLAIN).
               header("version", "1.0").
               queryParam("question", "Who is bill gates").
        when().
               get("/api/get/validate/RAVI").
        then().
               log().all().
               statusCode(200).
               contentType(MediaType.TEXT_PLAIN).
               extract().response();
	    
	    assertThat(response.getBody().asString(), equalTo("1.0 RAVI Who is bill gates"));
    }
	
    @Test
    public void should_return_406_for_post_request_with_out_content_type_header() {
        given().
               accept(MediaType.TEXT_PLAIN).
               contentType(MediaType.APPLICATION_JSON).
               body("{" +
                      "\"college\": \"Anna University Campus CEG\"," +
                      "\"age\": 20 " +
                    "}").
        when().
               post("/api/post/validate/RAVI").
        then().
               log().all().
               statusCode(406);
    }
	
    @Test
    public void should_return_415_for_post_request_with_invalid_content_type_header() {
        given().
               contentType(MediaType.TEXT_PLAIN).
        when().
               post("/api/post/validate/86").
        then().
               log().all().
               statusCode(415);
    }
	
    @Test
    public void should_return_400_for_post_request_with_invalid_name() {
        given().
               contentType(MediaType.APPLICATION_JSON).
        when().
               post("/api/post/validate/86").
        then().
               log().all().
               statusCode(400).
               contentType(APPLICATION_ERROR_JSON).
               body(ERROR_CODE, equalTo(400)).
               body(ERROR_MESSAGE, equalTo("Name format is incorrect"));
    }
	
    @Test
    public void should_return_400_for_post_request_with_invalid_college() {
        given().
               contentType(MediaType.APPLICATION_JSON).
               body("{" +
                       "\"college\": \"456\", " +
                       "\"age\": 20 " +
                     "}").
        when().
               post("/api/post/validate/RAVI").
        then().
               log().all().
               statusCode(400).
               contentType(APPLICATION_ERROR_JSON).
               body(ERROR_CODE, equalTo(400)).
               body(ERROR_MESSAGE, equalTo("College is not valid"));
    }

    @Test
    public void should_return_400_for_post_request_with_invalid_age() {
        given().
               contentType(MediaType.APPLICATION_JSON).
               body("{" +
                       "\"college\": \"Anna University Campus CEG\"," +
                       "\"age\": -1" +
                     "}").
        when().
               post("/api/post/validate/RAVI").
        then().
               log().all().
               statusCode(400).
               contentType(APPLICATION_ERROR_JSON).
               body(ERROR_CODE, equalTo(400)).
               body(ERROR_MESSAGE, equalTo("Age is not valid"));
    }
	
    @Test
    public void should_return_200_for_valid_post_request() {
        given().
               contentType(MediaType.APPLICATION_JSON).
               body("{" +
                       "\"college\": \"Anna University Campus CEG\"," +
                       "\"age\": 21" +
                     "}").
        when().
               post("/api/post/validate/RAVI").
        then().
               log().all().
               statusCode(200).
               contentType(MediaType.APPLICATION_JSON).
               body("college", equalTo("Anna University Campus CEG")).
               body("age", equalTo(21));
    }

}