package GETAPITestWithBDD;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class FakeStoreAPITest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://fakestoreapi.com";
    }

    @Test
    public void getAllProductsTest(){
        Response response=when()
                             .get("/products");

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        response.prettyPrint();
        JsonPath jsPath=response.jsonPath();

        List<Integer> idsList=jsPath.getList("id");
        System.out.println("Ids List: "+idsList);

        List<Double> priceList=jsPath.getList("price");
        System.out.println("Price List: "+priceList);

        List<Double> ratingRateList=jsPath.getList("rating.rate");
        System.out.println("Rating Rate List: "+ratingRateList);

        List<Integer> ratingCountList=jsPath.getList("rating.count");
        System.out.println("Rating Count List: "+ratingCountList);

        for(int i=0;i< idsList.size();i++){
            int id=idsList.get(i);
            Object price=priceList.get(i);
            Object rate=ratingRateList.get(i);
            int count=ratingCountList.get(i);

            System.out.println("Id:"+id +" == "+"price:"+price+ " == "+"rate:"+rate+" == "+"count:"+count);
        }
    }

    @Test
    public void getProductCountTest(){
                when()
                    .get("/products")
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body("$.size()", equalTo(20));
    }

}
