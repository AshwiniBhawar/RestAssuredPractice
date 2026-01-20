package SchemaValidation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

public class SchemaValidationTest {

    private String getRandomEmailID(){
        return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
    }

    @Test
    public void getUserSchemaTest(){
        RestAssured.baseURI="https://gorest.co.in";
        RestAssured.given()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                    .get("/public/v2/users")
                .then()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAllUsers.json"));
    }

    @Test
    public void creteAUserSchemaTest(){
        CreateUserLombok lombok= new CreateUserLombok("Ash B", "female", getRandomEmailID(), "active");

        RestAssured.baseURI="https://gorest.co.in";

        RestAssured.given()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .contentType(ContentType.JSON)
                .body(lombok)
                .when()
                .post("/public/v2/users")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUserSchema.json"));
    }


}
