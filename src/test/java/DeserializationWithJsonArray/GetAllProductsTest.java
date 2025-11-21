package DeserializationWithJsonArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllProductsTest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://fakestoreapi.com/";
    }

    @Test
    public void getAllProductsTest(){
        Response response=given()
                .when()
                .get("/products");

        response.prettyPrint();

        ObjectMapper mapper= new ObjectMapper();
        try {
            ProductsLombok userResponse[]= mapper.readValue(response.getBody().asString(), ProductsLombok[].class);
            for(ProductsLombok userRes:userResponse) {
                System.out.println("id:" + userRes.getId());
                System.out.println("title:" + userRes.getTitle());
                System.out.println("price:" + userRes.getPrice());
                System.out.println("description:" + userRes.getDescription());
                System.out.println("image:" + userRes.getImage());
                System.out.println("category:" + userRes.getCategory());
                System.out.println("rating rate:" + userRes.getRating().getRate());
                System.out.println("rating count:" + userRes.getRating().getCount());
                System.out.println("===============================================");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
