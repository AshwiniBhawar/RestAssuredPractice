package GETAPITestWithDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoRestGetAUser {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void getSingleUserUsingPOJOTest() {
        Response response = given()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                    .get("/public/v2/users/8229460");

        response.prettyPrint();
        //deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User userRes = objectMapper.readValue(response.getBody().asString(), User.class);
            System.out.println(userRes);
            Assert.assertEquals(userRes.getId(), 8229460);
            Assert.assertEquals(userRes.getName(), "Dhyaneshwar Patel");
            Assert.assertEquals(userRes.getGender(), "male");
            Assert.assertEquals(userRes.getStatus(), "inactive");
            Assert.assertEquals(userRes.getEmail(), "patel_dhyaneshwar@ruecker.example");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSingleUserUsingLombokTest(){
        Response response=given()
                    .header("Authorization","Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                    .get("/public/v2/users/8229460");

        response.prettyPrint();
        //deserialization
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            UserLombok userRes = objectMapper.readValue(response.getBody().asString(), UserLombok.class);
            System.out.println(userRes);
            Assert.assertEquals(userRes.getId(),8229460);
            Assert.assertEquals(userRes.getName(),"Dhyaneshwar Patel");
            Assert.assertEquals(userRes.getGender(),"male");
            Assert.assertEquals(userRes.getStatus(),"inactive");
            Assert.assertEquals(userRes.getEmail(),"patel_dhyaneshwar@ruecker.example");
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }

}
