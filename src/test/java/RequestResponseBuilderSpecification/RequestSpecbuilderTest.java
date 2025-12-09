package RequestResponseBuilderSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RequestSpecbuilderTest {
    public static RequestSpecification userReqSpec() {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .build();

        return reqSpec;
    }


    @Test
    public void getUsersTest() {
        RestAssured.given()
                .spec(userReqSpec())
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat().statusCode(200);

    }


    @Test
    public void getUsersWithQueryParamTest() {
        RestAssured.given()
                .queryParam("name", "naveen")
                .queryParam("status", "active")
                .spec(userReqSpec())
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat().statusCode(200);

    }

}
