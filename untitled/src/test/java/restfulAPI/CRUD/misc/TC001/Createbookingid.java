package restfulAPI.CRUD.misc.TC001;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restfulAPI.CRUD.misc.pojo.Bookingdates;
import restfulAPI.CRUD.misc.pojo.bookingid;

public class Createbookingid {
    @Test
            public void testbooking(){
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

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .when().body(createbooking).post()
                .then().log().all().statusCode(200);
    }



    }

