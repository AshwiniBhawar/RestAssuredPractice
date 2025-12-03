package XMLAPIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ErgastXMlAPITest {
    @BeforeTest
    public void setUp(){
        RestAssured.baseURI="http://ergast.com";
    }

    @Test
    public void getAllCircuitsXMlTest() {
        Response response = given().log().all()
                .when()
                .get("/api/f1/2017/circuits.xml");
        response.prettyPrint();
        System.out.println("-----------------------------------------------------");
        String responseBody=response.getBody().asString();

        //xml--pojo
        XmlMapper mapper= new XmlMapper();
        try {
            MRData mrData=mapper.readValue(responseBody, MRData.class);

            System.out.println("Series is:"+ mrData.getSeries());
            System.out.println("Url is:"+ mrData.getUrl());
            System.out.println("Limit is:"+ mrData.getLimit());
            System.out.println("Offest is:"+ mrData.getOffset());
            System.out.println("Total is:"+ mrData.getTotal());

            System.out.println("Season is:"+ mrData.getCircuitTable().getSeason());

            System.out.println("CircuitId is:"+ mrData.getCircuitTable().getCircuits().get(0).getCircuitId());
            System.out.println("CircuitUrl is:"+ mrData.getCircuitTable().getCircuits().get(0).getUrl());
            System.out.println("CircuitName is:"+ mrData.getCircuitTable().getCircuits().get(0).getCircuitName());

            System.out.println("Latitude is:"+ mrData.getCircuitTable().getCircuits().get(0).getLocation().getLatitude());
            System.out.println("Longitude is:"+ mrData.getCircuitTable().getCircuits().get(0).getLocation().getLongitude());

            System.out.println("Locality is:"+ mrData.getCircuitTable().getCircuits().get(0).getLocation().getLocality());
            System.out.println("Country is:"+ mrData.getCircuitTable().getCircuits().get(0).getLocation().getCountry());

            System.out.println("------------------------------------------------------------------------------");

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
