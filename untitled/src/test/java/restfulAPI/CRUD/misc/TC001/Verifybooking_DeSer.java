package restfulAPI.CRUD.misc.TC001;

import com.google.gson.Gson;
import restfulAPI.CRUD.misc.pojo.bookingresponse;

public class Verifybooking_DeSer {
    public static void main(String[] args) {
        String bookingresponse = "{\n" +
                "    \"bookingid\": 1820,\n" +
                "    \"booking\": {\n" +
                "        \"firstname\": \"Rohit\",\n" +
                "        \"lastname\": \"Gawli\",\n" +
                "        \"totalprice\": 10000,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2025-01-01\",\n" +
                "            \"checkout\": \"2025-01-06\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }\n" +
                "}";
        Gson gson = new Gson();

        bookingresponse verifybookingresponse = gson.fromJson(bookingresponse, bookingresponse.class);
        System.out.println(verifybookingresponse.getBookingid());
        System.out.println(verifybookingresponse.getBooking().getFirstname());



    }
}