package XMLAPIs;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GoRestUserXMLAPITest {

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void getAllUsersXMlTest1(){
        Response response=given().log().all()
                .when()
                .get("/public/v2/users.xml");
        response.prettyPrint();

        String responseBody=response.getBody().asString();      //fetch the response as String
        //create object of XmlPath
        XmlPath xmlPath= new XmlPath(responseBody);

        //fetching the data
        String objType=xmlPath.getString("objects.@type");
        System.out.println("object types is: "+objType);
        Assert.assertEquals(objType, "array");
        System.out.println("-------------------------------");
        List<String> idTypeList=xmlPath.getList("objects.object.id.@type");
        System.out.println("id type list :"+idTypeList);
        for(String type:idTypeList){
            Assert.assertEquals(type,"integer");
        }
        System.out.println("-------------------------------");

        List<Integer> idList=xmlPath.getList("objects.object.id", Integer.class);
        //List<Object> idList=xmlPath.getList("objects.object.id");
        System.out.println("id list :"+idList);
        for(Integer id:idList){
            Assert.assertNotNull(id);
        }
        System.out.println("-------------------------------");

        List<String> nameList=xmlPath.getList("objects.object.name");
        System.out.println("name list :"+nameList);
        for(String name:nameList){
            Assert.assertNotNull(name);
        }
        System.out.println("-------------------------------");

        List<String> emailList=xmlPath.getList("objects.object.email");
        System.out.println("email list :"+emailList);
        for(String email:emailList){
            Assert.assertNotNull(email);
        }
        System.out.println("-------------------------------");

        List<String> genderList=xmlPath.getList("objects.object.gender");
        System.out.println("gender list :"+genderList);
        for(String gender:genderList){
            Assert.assertNotNull(gender);
        }
        System.out.println("-------------------------------");

        List<String> statusList=xmlPath.getList("objects.object.status");
        System.out.println("status list :"+statusList);
        for(String status:statusList){
            Assert.assertNotNull(status);
        }
        System.out.println("-------------------------------");
    }

    @Test
    public void getAllUsersXMlTest2() {
        Response response = given().log().all()
                .when()
                .get("/public/v2/users.xml");
        response.prettyPrint();
        System.out.println("-------------------------------");

        String responseBody = response.getBody().asString();      //fetch the response as String
        //create object of XmlPath
        XmlPath xmlPath = new XmlPath(responseBody);

        //fetching the data
        List<String> objectList = xmlPath.getList("objects.object");
        System.out.println("object list size is:" + objectList.size());
        System.out.println("-------------------------------");

        for (int i=0;i< objectList.size();i++) {
            System.out.println("id is :" +xmlPath.getString("objects.object[" + i + "].id"));
            System.out.println("name is :" + xmlPath.getString("objects.object[" + i + "].name"));
            System.out.println("email is :" + xmlPath.getString("objects.object[" + i + "].email"));
            System.out.println("gender is :" + xmlPath.getString("objects.object[" + i + "].gender"));
            System.out.println("status is :" + xmlPath.getString("objects.object[" + i + "].status"));
            System.out.println("-------------------------------");
        }
    }
}
