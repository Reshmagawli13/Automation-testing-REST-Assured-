package restfulAPI.CRUD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class post {
    @Test
    public void postrequest() {
        // Step 1: Authenticate to get token
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com/");

        String payloadAuth = "{\n" +
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"password123\"\n" +
                "}";

        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payloadAuth);

        Response response = r.when().post();
        ValidatableResponse vr = response.then().log().all();
        vr.statusCode(200);

        // Token generated
        String token = response.then().extract().path("token");
        System.out.println(token);



        // Step 2: Create a new booking
        String payloadBooking = "{\n" +
                "  \"firstname\": \"Rohit\",\n" +
                "  \"lastname\": \"Gawli\",\n" +
                "  \"totalprice\": 111,\n" +
                "  \"depositpaid\": true,\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"2023-01-01\",\n" +
                "    \"checkout\": \"2023-01-01\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"Breakfast\"\n" +
                "}";

       r.basePath("booking");
       r.body(payloadBooking);
       response = r.when().post();
       vr = response.then().log().all();
       vr.statusCode(200);

        //Booking ID is generated
        Integer bookingid = response.then().extract().path("bookingid");

        //Step 3
        //Token and ID we get from Step 1 and Step 2
        // Now update the Name - Use PUT Request
        // and verify the response

        String payloadputrequest = "{\n" +
                "  \"firstname\": \"Reshma\",\n" +
                "  \"lastname\": \"Gawli\",\n" +
                "  \"totalprice\": 111,\n" +
                "  \"depositpaid\": true,\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"2023-01-01\",\n" +
                "    \"checkout\": \"2023-01-04\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        r.basePath("booking/"+bookingid);
        r.cookies("token",token);
        r.body(payloadputrequest);

        response = r.when().put();
        vr = response.then().log().all();

        // TC1 - validate the response
        vr.statusCode(200);
        //TC2 - validate the response is updated or not
        vr.body("firstname", Matchers.equalTo("Reshma"));

    }
}