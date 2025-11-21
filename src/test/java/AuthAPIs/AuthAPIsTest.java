package AuthAPIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthAPIsTest {

    @Test
    public void basicAuthTest(){
        RestAssured.baseURI="https://the-internet.herokuapp.com";

        Response response=given().log().all()
                    .auth()
                    .basic("admin","admin")
                .when()
                    .get("/basic_auth");
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        String resString=response.getBody().asString();
        Assert.assertTrue(resString.contains("Congratulations! You must have the proper credentials."));

    }

    @Test
    public void digestiveAuthTest(){
        RestAssured.baseURI="https://postman-echo.com";

        Response response=given().log().all()
                    .auth()
                    .digest("postman","password")
                .when()
                    .get("/digest-auth");
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        JsonPath jsonPath=response.getBody().jsonPath();
        Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("authenticated")));

    }

    @Test
    public void preemptiveAuthTest(){
        RestAssured.baseURI="https://the-internet.herokuapp.com";

        Response response=given().log().all()
                    .auth()
                    .preemptive()
                    .basic("admin","admin")
                .when()
                    .get("/basic_auth");
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        String resString=response.getBody().asString();
        Assert.assertTrue(resString.contains("Congratulations! You must have the proper credentials."));Assert.assertEquals(response.statusCode(),200);

    }
}
