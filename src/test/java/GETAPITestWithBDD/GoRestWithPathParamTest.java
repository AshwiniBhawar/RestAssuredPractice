package GETAPITestWithBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GoRestWithPathParamTest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void getAUserPostWithQueryParamTest() {
        Response response = given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .pathParams("userid", 7583038)
                .when()
                    .get("/public/v2/users/{userid}/posts");

        response.prettyPrint();
    }

    @DataProvider(name="userdata")
    public Object[][] getUserData(){
        Object[][] data= new Object[][]{
                {7583038, "Clam usus amplitudo admoneo una facere minima."},
                {8229626, "Aufero denego atavus magnam pecunia cultura ventus cervus."},
                {8229633, "Auctor tribuo basium arceo stillicidium calcar autem."}
        };
        return data;
    }

    @Test(dataProvider="userdata")
    public void getAUserPostWithQueryParamUsingDPTest(int userId, String title) {
                 given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .pathParams("userid", userId)
                .when()
                    .get("/public/v2/users/{userid}/posts")
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                 .body("title", hasItem(title));


    }

    @Test
    public void getAUserPostWithQueryParamUseMatcherTest() {
                given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .pathParams("userid", 7583038)
                .when()
                    .get("/public/v2/users/{userid}/posts")
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body("title", hasItem("Clam usus amplitudo admoneo una facere minima."));
    }
}
