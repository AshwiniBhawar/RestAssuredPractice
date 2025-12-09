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

public class FakeStoreAPIRegularExpressionTest {
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
        //Using regular expression
        List<Number> contains = context.read("$.[?(@.title=~ /.*Backpack.*/i)].id");
        System.out.println("Id contains: "+contains);

        List<Integer> startsWith = context.read("$.[?(@.title=~ /^Fjallraven.*/i)].id");
        System.out.println("Id starts-with Fjallraven : "+startsWith);

        List<Integer> endsWith = context.read("$.[?(@.title=~ /.*Laptops$/i)].id");
        System.out.println("Id ends-with Laptops : "+endsWith);

        System.out.println("***********************************************************");

        //using operators
        List<Number> prices = context.read("$.[?(@.price>50)].price");
        System.out.println("Prices list: "+prices);

        List<Integer> ids = context.read("$.[?(@.price>50)].id");
        System.out.println("Ids list : "+ids);

        List<Double> ratingRate = context.read("$.[?(@.price>50)].rating.rate");
        System.out.println("Rating rate : "+ratingRate);

        List<Integer> ratingCount = context.read("$.[?(@.price>50)].rating.count");
        System.out.println("Rating count : "+ratingCount);


    }
}
