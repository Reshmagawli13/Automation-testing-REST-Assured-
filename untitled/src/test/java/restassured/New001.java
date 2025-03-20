package restassured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;


public class New001 {
    @Test(groups = {"positive"})
    public void getRequest_Positive() {
        RestAssured
                .given().baseUri("https://digitest.commtelnetworks.work/")
                .when().get()
                .then().log().all().statusCode(200);
    }

    @Test(groups = {"negative"})
    public void getRequest_Negative() {
        RestAssured
                .given().baseUri("https://digitest.commtelnetworks.work/")
                .when().get()
                .then().log().all().statusCode(201);

    }
}
