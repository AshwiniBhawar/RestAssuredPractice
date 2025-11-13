package GETAPITestWithBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.*;

public class BookingsAPITest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
    }

    @Test
    public void getAllContactsAPITest(){
        given().log().all()
                .when()
                    .get("/booking")
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .contentType(ContentType.JSON)
                .and()
                    .header("Server","Heroku");
    }

    @Test
    public void getContactsAndValidateAContactAPITest() {
        List<Integer> bookingIds = given().log().all()
                .when()
                .get("/booking")
                .then().log().all()
                .extract()
                .path("bookingid");

        System.out.println("Size of ids:" + bookingIds.size());

        Assert.assertTrue(!bookingIds.isEmpty());
        Assert.assertTrue(bookingIds.containsAll(List.of(33, 1)),"Booking IDs list does not contain expected IDs");
    }
}
