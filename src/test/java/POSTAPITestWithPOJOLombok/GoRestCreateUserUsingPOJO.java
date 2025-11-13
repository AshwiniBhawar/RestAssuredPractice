package POSTAPITestWithPOJOLombok;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestCreateUserUsingPOJO {

    public String getRandomEmailID(){
        return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
    }

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void createAUser(){
        User user= new User("Ash B", "female", getRandomEmailID(), "active");

        //1. Create a user---POST -using pojo to json(serialization) using jackson(databind) lib
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
    }
}
