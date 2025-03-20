package restfulAPI.CRUD.misc;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class misc001 {

    @Test
    public void postrequest() {
        // Step 1: Authenticate to get token
        //keep your payload i 3 ways
        //1. string , 2. hashmap, 3. class & object

        Map<String, String> h = new HashMap<>();
        h.put("name", "pramod");
        System.out.println(h);

        Map<String, Object> jsonBodyMap = new LinkedHashMap<>();
        jsonBodyMap.put("firstname", "Pramod");
        jsonBodyMap.put("lastname", "Patil");
        jsonBodyMap.put("totalprice", 23232);
        jsonBodyMap.put("depositpaid", true);
        jsonBodyMap.put("additionalneeds", "Lunch");

        Map<String, String> bookingDateMap = new LinkedHashMap<>();
        bookingDateMap.put("checkin", "2025-03-18");
        bookingDateMap.put("checkout", "2025-03-21");

        jsonBodyMap.put("bookingdates", bookingDateMap);
        System.out.println(jsonBodyMap);

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .body(jsonBodyMap)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200); // Expecting 201 Created
    }
}
