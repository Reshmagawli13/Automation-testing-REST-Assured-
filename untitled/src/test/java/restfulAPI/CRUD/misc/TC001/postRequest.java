package restfulAPI.CRUD.misc.TC001;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import restfulAPI.CRUD.misc.pojo.Bookingdates;
import restfulAPI.CRUD.misc.pojo.bookingid;
import restfulAPI.CRUD.misc.pojo.bookingresponse;

public class postRequest {
    @Test
    public void postRequestTC1(){
        Gson gson;
        gson = new Gson();
        bookingid createbooking = new bookingid();
        createbooking.setFirstname("Reshma");
        createbooking.setLastname("Gawli");
        createbooking.setTotalprice(4500);
        createbooking.setDepositpaid(true);
        createbooking.setAdditionalneeds("Breakfast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2021-04-20");
        bookingdates.setCheckout("2021-04-22");

        createbooking.setBookingdates(bookingdates);
        System.out.println(createbooking);

        RequestSpecification r = RestAssured.given();
                r.baseUri("https://restful-booker.herokuapp.com/");
                r.basePath("/booking");
                r.contentType(ContentType.JSON);

        Response response = r.when().body(createbooking).post();
        ValidatableResponse vr = response.then().log().all().statusCode(200);
        String responseString = response.asString();

        bookingresponse bookingresponse = gson.fromJson(responseString, bookingresponse.class);
        System.out.println(bookingresponse.getBookingid());

        Assert.assertNotNull(bookingresponse.getBookingid());
        Assert.assertEquals(bookingresponse.getBooking().getFirstname(),"Reshma");
    }


    }

