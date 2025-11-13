package GETAPITestWithBDD;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestAPITest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void getAUserTest(){
        Response response=given().log().all()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                .get("/public/v2/users/8229669");

        System.out.println("Status Code:" + response.statusCode());
        System.out.println("Status Line:" + response.statusLine());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.statusLine().contains("200 OK"));

        response.prettyPrint();

        //fetch the body
        JsonPath js= response.jsonPath();
        Assert.assertEquals(js.getInt("id"),8229669);
        Assert.assertEquals(js.getString("name"),"Aarya Menon");
        Assert.assertEquals(js.getString("email"),"menon_aarya@lehner.example");
        Assert.assertEquals(js.getString("gender"),"female");
        Assert.assertEquals(js.getString("status"),"inactive");
    }

    @Test
    public void getAllUsersTest(){
        Response response=given().log().all()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                .get("/public/v2/users");

        System.out.println("Status Code:" + response.statusCode());
        System.out.println("Status Line:" + response.statusLine());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.statusLine().contains("200 OK"));

        response.prettyPrint();

        //fetch the body
        JsonPath js= response.jsonPath();
        List<Integer> idsList=js.getList("id");
        System.out.println(idsList);
        List<Integer> namesList=js.getList("name");
        System.out.println(namesList);
        System.out.println("Size of list:"+idsList.size());

    }

}
