package SSLCertificate;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ExpiredSSLCertificateTest {

    @Test
    public void sslCertificateTest(){
        given()
                .relaxedHTTPSValidation()
                .when()
                .get("https://untrusted-root.badssl.com/")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void sslCertificateUsingRAConfigTest(){
        RestAssured.config=RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
        given()
                .when()
                .get("https://untrusted-root.badssl.com/")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
