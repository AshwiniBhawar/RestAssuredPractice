package JaywayJsonPath;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FakeStoreAPIOperatorTest {
    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @Test
    public void getProductIdsAPIJsonTest() {
        Response response = given()
                .when()
                .get("/products");

        String jsonResponse = response.getBody().asString();
        ReadContext context = JsonPath.parse(jsonResponse);
        //Single attribute
        List<Integer> idsList = context.read("$.[*].id");
        System.out.println(idsList);
        System.out.println("Size of id list: " + idsList.size());
    }

    @Test
    public void getProductDetailsAPIJsonTest() {
        Response response = given()
                .when()
                .get("/products");

        String jsonResponse = response.getBody().asString();
        ReadContext context = JsonPath.parse(jsonResponse);
        //Multiple attribute

        List<Map<String, Object>> twoAttributelist = context.read("$.[*].['id','title']");
        System.out.println("Size of list: " + twoAttributelist.size());
        System.out.println(twoAttributelist);

        for (Map<String, Object> e : twoAttributelist) {
            int id = (int) e.get("id");
            String title = (String) e.get("title");
            System.out.println("Id: " + id);
            System.out.println("Title: " + title);
            System.out.println("------------------------------------------------");
        }

        System.out.println("**************************************************");
        List<Map<String, Object>> threeAttributelist = context.read("$.[*].['id','title','category']");
        System.out.println("Size of list: " + threeAttributelist.size());
        System.out.println(threeAttributelist);

        for (Map<String, Object> e : threeAttributelist) {
            int id = (int) e.get("id");
            String title = (String) e.get("title");
            String category = (String) e.get("category");
            System.out.println("Id: " + id);
            System.out.println("Title: " + title);
            System.out.println("Category: " + category);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("**************************************************");

        List<Number> singleAttributelist = context.read("$.[*].rating.rate");
        System.out.println("Size of list: " + singleAttributelist.size());
        System.out.println(singleAttributelist);
        for (Number d : singleAttributelist) {
            System.out.println(d);              //Use Number instead of D ouble to avoid ClassCastException
        }
        System.out.println("**************************************************");

        //$.[?(@.category=='jewelery')].id
        //$.[?(@.category=='jewelery')].title
        //$.[?(@.category=='jewelery')].rating.rate
        //$.[?(@.category=='jewelery')].['id','title']
        //$.[?((@.category=='jewelery') && (@.price <10))].id
        //$.[?((@.category=='jewelery') && (@.price <10))].['id','title']
        //$.[?((@.category=='jewelery') && (@.price <10) &&(@.rating.rate==3))].['id']
        //min($.[*].price)
        //max($.[*].price)
        //avg($.[*].price)
        //length($)

        List<Map<String, Object>> jewlIdTitleList = context.read("$.[?(@.category=='jewelery')].['id','title']");
        System.out.println("Size of list: " + jewlIdTitleList.size());
        System.out.println(jewlIdTitleList);

        for (Map<String, Object> e : jewlIdTitleList) {
            int id = (int) e.get("id");
            String title = (String) e.get("title");
            System.out.println("Id: " + id);
            System.out.println("Title: " + title);
            System.out.println("------------------------------------------------");
        }
        System.out.println("**************************************************");
    }
}
