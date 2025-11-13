package GETAPITestWithNonBDD;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FakeStoreAPITest {
    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://fakestoreapi.com";
    }

    @Test
    public void getAllProductsTest() {
       // RequestSpecification request = RestAssured.given(); //If you have headers, query, path
        RequestSender request= RestAssured.when();            //else you can directly use
        Response response=request.get("/products");
        System.out.println("Status code is :"+response.statusCode());
        System.out.println("Status line is :"+response.statusLine());
        response.prettyPrint();
        String contentType=response.header("content-type");
        System.out.println("Content type is :"+contentType);

        System.out.println("=================");

        Headers headers=response.headers();
        List<Header> headersList=headers.asList();
        System.out.println("Size of headers list :"+headersList.size());
        for(Header header:headersList){
            System.out.println(header.getName()+" : "+header.getValue());
        }
    }
}
