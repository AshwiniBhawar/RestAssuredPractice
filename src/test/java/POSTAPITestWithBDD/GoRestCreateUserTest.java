package POSTAPITestWithBDD;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class GoRestCreateUserTest {

    public String getRandomEmailID(){
        return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
    }

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void createUserWithJSONStringTest(){
            given().log().all()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .contentType(ContentType.JSON)
                .body("{\n" +
                "    \"name\": \"Ash B\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"email\": \"ash.b@yahoo.com\",\n" +
                "    \"status\": \"active\"\n" +
                "}")
            .when()
                .post("/public/v2/users")
            .then().log().all()
            .assertThat()
                .statusCode(201);
    }

    @Test
    public void createUserWithJSONStringVarTest(){
        String emailId=getRandomEmailID();
        String jsonData="{\n" +
                "    \"name\": \"Ash B\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"email\": \""+emailId+"\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(jsonData)
                .when()
                    .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                    .statusCode(201);
    }

    @Test
    public void createUserWithJSONFileTest(){
        given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body( new File("./src/test/resources/jsonfiles/user.json"))
                .when()
                    .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                    .statusCode(201);
    }

    @Test
    public void createUserWithJSONFileWithStringReplacementTest(){
        String emailID= getRandomEmailID();
        String rawJson=null;
        try {
             rawJson= new String(Files.readAllBytes(Paths.get("./src/test/resources/jsonfiles/user_randomemail.json")));
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("file not found:");
        }
        String updatedJson=rawJson.replace("{{emailID}}", emailID);

        Integer id=given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(updatedJson)
                .when()
                    .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                    .statusCode(201)
                .and()
                .extract()
                    .path("id");
        System.out.println("New user id is:"+id);
    }
}
