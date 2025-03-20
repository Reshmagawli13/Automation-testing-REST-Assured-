package restfulAPI.CRUD.misc.TC001;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restfulAPI.CRUD.misc.pojo.Auth;

public class Post_auth {

    @Test
    public void testauth(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .when().body(auth).post()
                .then().log().all().statusCode(200);

    }

}
