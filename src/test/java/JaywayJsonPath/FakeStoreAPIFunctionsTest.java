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

public class FakeStoreAPIFunctionsTest {
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
        //Using conatins
        Integer minId = context.read("min($.[*].id)", Integer.class);
        System.out.println("Min id: "+minId);

        Integer maxId = context.read("max($.[*].id)", Integer.class);
        System.out.println("Max id: "+maxId);

        Double avgPrice = context.read("avg($.[*].price)");
        System.out.println("Avg of prices: "+avgPrice);

        Double sumPrice = context.read("sum($.[*].price)");
        System.out.println("Sum of prices: "+sumPrice);

        Integer lengthId = context.read("length($)");
        System.out.println("Length of an array: "+lengthId);

        Map<String,Object> firstElement = context.read("first($)");
        System.out.println("First element: "+firstElement);

        Integer firstElementID = context.read("first($).id", Integer.class);
        System.out.println("First element id: "+firstElementID);

        Map<String,Object> lastElement = context.read("last($)");
        System.out.println("Last element: "+lastElement);

        Integer lastElementID = context.read("last($).id", Integer.class);
        System.out.println("Last element id: "+lastElementID);

        Map<String,Object> indexElement = context.read("index($.[*],2)");
        System.out.println("Element at index 2: "+indexElement);

    }
}
