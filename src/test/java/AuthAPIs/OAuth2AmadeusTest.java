package AuthAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OAuth2AmadeusTest {
    String accessToken;
    @BeforeMethod
    public void getAccessToken() {
        Map<String, String> map= new HashMap<>();
        map.put("grant_type","client_credentials");
        map.put("client_id","47Ae5NUW3JK1AzmHO6AGdANAjAcSJ6D0");
        map.put("client_secret","KRBtzUdpBgSol6Kz");
        RestAssured.baseURI="https://test.api.amadeus.com";

        Response response=given().log().all()
                    .formParams(map)
                    .contentType(ContentType.URLENC)
                .when()
                    .post("/v1/security/oauth2/token");
        Assert.assertEquals(response.getStatusCode(),200);
        accessToken=response.jsonPath().getString("access_token");
    }

        @Test
        public void getFlightDetailsAmadeusTest(){
        RestAssured.baseURI="https://api.twitter.com";

        given().log().all()
                    .header("Authorization", "Bearer "+accessToken)
                .when()
                    .get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200")
                .then().log().all()
                .assertThat()
                    .statusCode(200);
    }

    @Test
    public void getFlightDetailsAmadeusOAuth2Test(){
        RestAssured.baseURI="https://api.twitter.com";

        given().log().all()
                    .auth()
                    .oauth2(accessToken)        //no need to add Bearer keyword.
                .when()
                    .get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200")
                .then().log().all()
                .assertThat()
                    .statusCode(200);
    }
}
