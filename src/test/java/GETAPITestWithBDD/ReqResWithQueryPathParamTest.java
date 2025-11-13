package GETAPITestWithBDD;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class ReqResWithQueryPathParamTest {
    //https://reqres.in/api/users?page=2
    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://reqres.in";
    }

    @Test
    public void getAUserPostWithQueryParamTest() {
        Map<String, String> pathParam= new HashMap<>();
        pathParam.put("firstpath","api");
        pathParam.put("secondpath", "users");

        given().log().all()
                    .pathParams(pathParam)
                    .queryParam("page",2)
                .when()
                    .get("/{firstpath}/{secondpath}")
                .then().log().all()
                .assertThat()
                    .statusCode(200);
    }
}
