package RequestResponseSpecification;

import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class ResponseSpecificationTest {
    @Test
    public void responseSpecTest() {
        ResponseSpecification resSpec = expect()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare");

        given()
                .baseUri("https://gorest.co.in")
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                .get("/public/v2/users")
                .then()
                .spec(resSpec);
    }


}
