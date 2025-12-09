package RequestResponseSpecification;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class RequestSpecificationTest {

    @Test
    public void requestSpecificationTest() {
        RequestSpecification requestSpec = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json");

        requestSpec.get("/posts")
                .then().log().all()
                .assertThat()
                .statusCode(200);

        requestSpec.get("/comments")
                .then().log().all()
                .assertThat()
                .statusCode(200);

        requestSpec.body("{\n" +
                        "    \"title\": \"foo\",\n" +
                        "    \"body\": \"bar\",\n" +
                        "    \"userId\": 1\n" +
                        "}")
                .when()
                .post("/posts")
                .then().log().all()
                .statusCode(201);
    }

    @Test
    public void requestSpecificationGoRestTest() {
        RequestSpecification requestSpec = RestAssured.given()
                .baseUri("https://gorest.co.in")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5");

        requestSpec.get("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(200);

        requestSpec.body("{\n" +
                        "    \"name\": \"Ash B\",\n" +
                        "    \"gender\": \"female\",\n" +
                        "    \"email\": \"ash.bsdxsxas@yahoo.com\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when()
                .post("/public/v2/users")
                .then().log().all()
                .statusCode(201);
    }
}