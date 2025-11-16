package APIChianingTestWithBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingAPIChainingTest {
    String token;
    int bookingID;
    @BeforeTest
    public void getToken(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        Credential creds= new Credential("admin", "password123");
        token=given().log().all()
                    .contentType(ContentType.JSON)
                    .body(creds)
                .when()
                    .post("/auth")
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .extract()
                    .path("token");
        System.out.println("Token is: "+token);
    }

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        bookingID=createABooking();
    }

    @Test()
    public void getBookingTest(){
       Response response= given().log().all()
                    .pathParam("id", bookingID)
                .when()
                    .get("/booking/{id}");
        response.prettyPrint();
        JsonPath jsonPath= response.getBody().jsonPath();

        Assert.assertEquals(jsonPath.getString("firstname"), "Jim");
        Assert.assertEquals(jsonPath.getString("lastname"), "Brown");
        Assert.assertEquals(jsonPath.getInt("totalprice"), 111);
        Assert.assertTrue(jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01");
        Assert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01");
        Assert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast");
    }

    @Test()
    public void creatABookingTest(){
        Assert.assertNotNull(bookingID);
    }

    @Test
    public void updateABookingTest(){
        given().log().all()
                    .contentType(ContentType.JSON)
                    .header("Cookie", "token="+token)
                    .header("Accept", "application/json")
                    .pathParam("id", bookingID)
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Dinner\"\n" +
                        "}")
                .when()
                    .put("/booking/{id}")
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                .body("additionalneeds", equalTo("Dinner"))
                .body("firstname", equalTo("James"));
    }

    @Test
    public void deleteABookingTest(){
        given().log().all()
                    .contentType(ContentType.JSON)
                    .header("Cookie", "token="+token)
                    .pathParam("id", bookingID)
                .when()
                    .delete("/booking/{id}")
                .then().log().all()
                .assertThat()
                    .statusCode(201);
    }

    public int createABooking(){
        int bookingID=given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when()
                    .post("/booking")
                .then()
                .assertThat()
                    .statusCode(200)
                .and()
                    .extract()
                    .path("bookingid");
           System.out.println("Booking id is: "+bookingID);

        return bookingID;
    }
}
