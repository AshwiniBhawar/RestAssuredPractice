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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreatePETTestWithoutBuilder {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://petstore3.swagger.io/";
    }

    @Test
    public void createAPetTest(){
        //post
        //serialization- pojo to json
        PetLombok.Category category= new PetLombok.Category(1,"Dog");

        PetLombok.Tags tag1= new PetLombok.Tags(1, "Red");
        PetLombok.Tags tag2= new PetLombok.Tags(2, "Black");
        List<PetLombok.Tags> tags=Arrays.asList(tag1, tag2);

        List<String> photoUrls= Arrays.asList("https://www.abc.com", "https://www.xyz.com");

        PetLombok petData= new PetLombok(101, "tommy", category, photoUrls, tags, "available");

        Response response=given().log().all()
                .contentType(ContentType.JSON)
                .body(petData)
            .when().log().all()
                .post("api/v3/pet");          //serialization will happen automatically

        response.prettyPrint();
        JsonPath jsonPath= response.jsonPath();

        //deserialization
        ObjectMapper mapper= new ObjectMapper();
        try {
            PetLombok petResp=mapper.readValue(response.getBody().asString(), PetLombok.class);

            Assert.assertEquals(petResp.getName(), petData.getName());
            Assert.assertEquals(petResp.getId(), petData.getId());
            Assert.assertEquals(petResp.getStatus(), petData.getStatus());
            Assert.assertEquals(petResp.getCategory().getId(), petData.getCategory().getId());
            Assert.assertEquals(petResp.getCategory().getName(), petData.getCategory().getName());
            Assert.assertEquals(petResp.getPhotoUrls().get(0), petData.getPhotoUrls().get(0));
            Assert.assertEquals(petResp.getPhotoUrls().get(1), petData.getPhotoUrls().get(1));
            Assert.assertEquals(petResp.getTags().get(0).getId(), petData.getTags().get(0).getId());
            Assert.assertEquals(petResp.getTags().get(1).getId(), petData.getTags().get(1).getId());
            Assert.assertEquals(petResp.getTags().get(0).getName(), petData.getTags().get(0).getName());
            Assert.assertEquals(petResp.getTags().get(1).getName(), petData.getTags().get(1).getName());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
