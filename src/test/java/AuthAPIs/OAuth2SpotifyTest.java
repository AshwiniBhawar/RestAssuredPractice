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

public class OAuth2SpotifyTest {

    String accessToken;
    @BeforeMethod
    public void getAccessToken() {
        Map<String, String> map= new HashMap<>();
        map.put("grant_type","client_credentials");
        map.put("client_id","ba2ee56323960af634c4c3");
        map.put("client_secret","3c42f34426b702fd105313d3");
        RestAssured.baseURI="https://accounts.spotify.com";

        Response response=given().log().all()
                    .formParams(map)
                    .contentType(ContentType.URLENC)
                .when()
                    .post("/api/token");
        Assert.assertEquals(response.getStatusCode(),200);
        accessToken=response.jsonPath().getString("access_token");
        System.out.println("Access token: "+accessToken);
    }

    @Test
    public void getAlbumTwitterTest(){
        RestAssured.baseURI="https://api.spotify.com";

        given().log().all()
                     .auth()
                     .oauth2(accessToken)
                .when()
                     .get("/v1/albums/4aawyAB9vmqN3uQ7FjRGTy")
                .then().log().all()
                .assertThat()
                     .statusCode(200);
    }
}
