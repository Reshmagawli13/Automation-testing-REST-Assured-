package restfulAPI.CRUD.IntScenario;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class INT01 {

    //step 1 - get token
    @Test
    public void newtokenrequest () {
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com/");

        String payloadAuth1 = "{\n" +
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"password123\"\n" +
                "}";

        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payloadAuth1);

        Response response = r.when().post();
        ValidatableResponse vr = response.then().log().all();
        String token = response.then().extract().path("token");
        System.out.println(token);

        //step 2 - create booking and get ID

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

        Integer bookingid = response.then().extract().path("bookingid");


       // Integer bookingid = response.then().extract().path("bookingid");

//step 3 - patch the booking with token and ID

        String partialupdatepatch = "{\n" +
                "  \"firstname\": \"Swapnil\",\n" +
                "  \"lastname\": \"Pawar\"\n" +
                "}";

        r.basePath("booking/" + bookingid);
        r.cookies("token", token);
        r.body(partialupdatepatch);
        response = r.when().patch();
        vr = response.then().log().all();
        vr.statusCode(200);
        vr.body("firstname", Matchers.equalTo("Swapnil"));



        //step 4 - delete the booking ID

        r.basePath("booking/" +bookingid);
        r.cookies("token",token);
        response = r.when().delete();

        vr = response.then().log().all();
        vr.statusCode(201);

    }


}
