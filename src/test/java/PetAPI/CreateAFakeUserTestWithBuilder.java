package PetAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateAFakeUserTestWithBuilder {

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

        UserLombok.Address.GeoLocation location= new UserLombok.Address.GeoLocation.GeoLocationBuilder().lat("12.25").longitude("23.66").build();

        UserLombok.Address address= new UserLombok.Address.AddressBuilder().geolocation(location).city("kilcoole")
                                                .street("new road").number(101).zipcode("123456").build();

        UserLombok.Name names=new UserLombok.Name.NameBuilder().firstname("Ash").lastname("B").build();

        UserLombok userLombok= new UserLombok.UserLombokBuilder().email(emailID).username(emailID).password("test1234")
                                                .phone("12926-3874").address(address).name(names).build();

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
        Assert.assertNotNull(getResp.getBody().jsonPath().getInt("id"));
    }

}
