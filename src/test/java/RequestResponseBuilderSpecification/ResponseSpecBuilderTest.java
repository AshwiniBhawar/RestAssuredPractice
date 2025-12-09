package RequestResponseBuilderSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

public class ResponseSpecBuilderTest {
    public static ResponseSpecification responseSpec() {
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("server", "cloudflare")
                .build();

        return resSpec;
    }

    public static ResponseSpecification responseSpec_401_AuthFail() {
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(401)
                .expectHeader("server", "cloudflare")
                .build();

        return resSpec;
    }

    @Test
    public void getUsersTest() {

        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.given()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(responseSpec());

    }

    @Test
    public void getUsersAuthFailTest() {

        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.given()
                .header("Authorization", "Bearer test")
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(responseSpec_401_AuthFail());

    }
}
