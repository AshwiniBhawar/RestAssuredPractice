package PUTAPITestWithBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestUpdateUser {

    public String getRandomEmailID(){
        return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
    }

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void createAUserTest(){
        String emailID= getRandomEmailID();

        User user= new User("Ash B", "female", emailID, "active");

        //1.Create a user --POST
        Integer userID=given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                    .statusCode(201)
                .extract()
                    .path("id");
        System.out.println("New user id is:"+userID);

        System.out.println("=================================");

        //2.Get a user---GET
        given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                    .get("/public/v2/users/"+userID)
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body("id", equalTo(userID));

        System.out.println("=================================");

        //3.Update a user---PUT
        user.setName("Riya T");
        user.setStatus("inactive");

        given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .put("/public/v2/users/"+userID)
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body("id", equalTo(userID))
                    .body("name", equalTo(user.getName()))
                    .body("status", equalTo(user.getStatus()));

        System.out.println("=================================");

        //4.Get a user---GET
        given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                    .get("/public/v2/users/"+userID)
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body("id", equalTo(userID))
                    .body("gender", equalTo(user.getGender()))
                    .body("name", equalTo(user.getName()))
                    .body("email", equalTo(user.getEmail()))
                    .body("status", equalTo(user.getStatus()));

    }
}
