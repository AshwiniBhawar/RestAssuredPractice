package XMLAPIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GoRestXMLAPIDeserializationTest {

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void getAllUsersXMlTest() {
        Response response = given().log().all()
                    .header("Accept", "application/xml")
                .when()
                    .get("/public/v2/users.xml");
        response.prettyPrint();
        System.out.println("-------------------------------");

        //xml to pojo--deserialization
        String responseBody = response.getBody().asString();      //fetch the response as String
        XmlMapper xmlMapper= new XmlMapper();
        try {
            UserData userData=xmlMapper.readValue(responseBody,UserData.class);
            System.out.println("object size:"+userData.getObject().size());
            System.out.println("object type is :" + userData.getType());
            System.out.println("-------------------------------");

            for(int i=0;i<userData.getObject().size();i++) {
                System.out.println("id is :" + userData.getObject().get(i).getId().getValue());
                System.out.println("id type is :" + userData.getObject().get(i).getId().getType());
                System.out.println("name is :" + userData.getObject().get(i).getName());
                System.out.println("email is :" + userData.getObject().get(i).getEmail());
                System.out.println("gender is :" + userData.getObject().get(i).getGender());
                System.out.println("status is :" + userData.getObject().get(i).getStatus());
                System.out.println("-------------------------------");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
