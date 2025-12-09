package RequestResponseSpecification;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestSpecificationReUseTest {
    RequestSpecification requestSpec;

    @BeforeMethod
    public void setup() {
        requestSpec = RestAssured.given().log().all()
                .baseUri("https://gorest.co.in/")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5");

    }

    @Test
    public void getUserTest() {
        requestSpec.when()
                .get("/public/v2/users")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void getSingleUserTest() {
        requestSpec.when()
                .get("/public/v2/users/8285138")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void getInvalidUserTest() {
        requestSpec.when()
                .get("/public/v2/users/9090")
                .then()
                .assertThat().statusCode(404);
    }
}
