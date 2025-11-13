package GETAPITestWithBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GoRestWithQueryParamTest {
    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void getAllUsersWithQueryParamTest() {
        Response response=given().log().all()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .queryParam("name","Umang")
                .queryParam("status","active")
                .when()
                .get("/public/v2/users");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.statusLine().contains("200 OK"));
        Assert.assertTrue(response.contentType().contains("json"));
        response.prettyPrint();
    }

    @Test
    public void getAllUsersWithQueryParamUsingHashMapTest() {

        Map<String, String> queryMap= new HashMap<>();
        queryMap.put("name","Umang");
        queryMap.put("status","inactive");

        Response response=given().log().all()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .queryParams(queryMap)
                .when()
                .get("/public/v2/users");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.statusLine().contains("200 OK"));
        Assert.assertTrue(response.contentType().contains("json"));
        response.prettyPrint();
    }
}
