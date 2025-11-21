package AuthAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class APIKey {

    @Test
    public void getAlbumsTest() {
        RestAssured.baseURI = "https://generativelanguage.googleapis.com";

        RestAssured.given().log().all()
                .queryParam("key", "AIzaSyBR7n4Pe7SkmcmNiTjU-xJgieQI")
                .contentType(ContentType.JSON)
                .body("{\n"
                        + "    \"contents\": [\n"
                        + "        {\n"
                        + "            \"parts\": [\n"
                        + "                {\n"
                        + "                    \"text\": \"Explain how xpath works in selenium\"\n"
                        + "                }\n"
                        + "            ]\n"
                        + "        }\n"
                        + "    ]\n"
                        + "}")

                .when().urlEncodingEnabled(false)
                .post("/v1beta/models/gemini-2.0-flash:generateContent")
                .then().log().all()
                .assertThat()
                .statusCode(200);

    }
}
