package PetAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateAFakeUserTestWithoutBuilder {

    public String generateEmail(){
        return "apiautomation"+System.currentTimeMillis()+"@fakestore.com";
    }

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://fakestoreapi.com";
    }

    @Test
    public void createAUserTest(){

        String emailID=generateEmail();

        UserLombok.Address.GeoLocation location= new UserLombok.Address.GeoLocation("12.25","23.66");

        UserLombok.Address address= new UserLombok.Address(location,"kilcoole","new road", 101, "123456");

        UserLombok.Name names=new UserLombok.Name("Ash", "B");

        UserLombok userLombok= new UserLombok(emailID, emailID, "test1234", "12926-3874", address, names);

        Response response=given().log().all()
                    .contentType("application/json")
                    .body(userLombok)
                .when()
                    .post("/users");        //serialization automatic

        response.prettyPrint();
        JsonPath userResp=response.getBody().jsonPath();

        int id= userResp.getInt("id");
        System.out.println("User id is : "+id);

        System.out.println("==============================================");
        //get
        Response getResp=given().log().all()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/users/"+id);

        getResp.prettyPrint();
        Assert.assertNotNull(getResp.jsonPath().getInt("id"));
    }

}
