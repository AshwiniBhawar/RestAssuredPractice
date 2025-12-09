package RequestResponseBuilderSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

public class RequestResponseSpecTest {
    public static RequestSpecification userReqSpec() {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .build();

        return reqSpec;
    }

    public static ResponseSpecification responseSpec() {
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("server", "cloudflare")
                .build();

        return resSpec;
    }


    @Test
    public void getUsersTest() {

        RestAssured.given()
                .spec(userReqSpec())
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(responseSpec());
    }
}
