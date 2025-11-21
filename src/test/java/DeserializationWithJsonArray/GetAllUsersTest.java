package DeserializationWithJsonArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllUsersTest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void getAUserTest() {
        Response response = given().log().all()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                .get("/public/v2/users");
        response.prettyPrint();

        //Deserialization- json to pojo
        ObjectMapper mapper= new ObjectMapper();
        try {
            //json array as response
            UserLombok userRes[] = mapper.readValue(response.getBody().asString(), UserLombok[].class);
            System.out.println(userRes);
            for(UserLombok user: userRes){
                System.out.println("id:"+user.getId());
                System.out.println("name:"+user.getName());
                System.out.println("gender:"+user.getGender());
                System.out.println("email:"+user.getEmail());
                System.out.println("status:"+user.getStatus());
            }
        }
        catch(JsonMappingException e){
            e.printStackTrace();
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
