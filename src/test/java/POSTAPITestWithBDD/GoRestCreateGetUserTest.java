package POSTAPITestWithBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestCreateGetUserTest {

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
        String rawJson=null;
        //convert json file content to string
        try {
            rawJson= new String(Files.readAllBytes(Paths.get("./src/test/resources/jsonfiles/user_randomemail.json")));
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("file not found:");
        }
        String updatedJson=rawJson.replace("{{emailID}}", emailID);

        //1.Create a user --POST
        Integer userID=given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(updatedJson)
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
