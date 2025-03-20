package restassured.Createrequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Clientmaster {

    @BeforeTest
    public void validateheaders(){
        RestAssured.baseURI = "https://digitest.commtelnetworks.work/";
        ResponseSpecification r = given()
                .header("CF-Access-Client-Id","f8582dae2c9595dc7bee7b23f059e37c.access\n")
                .header("CF-Access-Client-Secret","f5f823101411a48c7614eb643fdc4097bf345f98cf65dc9a230994cb7ac23bba\n")
                .when()
                .param("Text","Login")
                .then()
                .expect().response();
    }
    @Test
    public void postrequest(){
        String payload = "{\n" +
                " \"username\" : \"Reshma.gawli@commtelnetworks.ai\",\n" +
                " \"Password\" : \"Devintra@123\"\n" +
                "}";
        RestAssured
                .given()
                .baseUri("https://digitest.commtelnetworks.work/")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .when().body(payload).post()
                .then().log().all().statusCode(302);
    }

    }

