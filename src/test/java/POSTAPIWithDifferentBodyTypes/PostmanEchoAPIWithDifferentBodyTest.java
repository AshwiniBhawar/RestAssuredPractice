package POSTAPIWithDifferentBodyTypes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PostmanEchoAPIWithDifferentBodyTest {

    @Test
    public void bodyWithJavascriptTest(){
        RestAssured.baseURI="https://postman-echo.com";
        String payload="<script>\n" +
                "    let greet = \"Hello World!\";\n" +
                "    document.write(greet);\n" +
                "    </script>";

        given().log().all()
                    .contentType("application/javascript; charset=utf-8")
                    .body(payload)
                .when()
                    .post("/post")
                .then().log().all()
                .assertThat()
                    .statusCode(200);
    }

    @Test
    public void bodyWithTextTest(){
        RestAssured.baseURI="https://postman-echo.com";
        String payload="Hi, I am practicing api automation";

        given().log().all()
                .contentType(ContentType.TEXT)
                .body(payload)
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithHTMLTest(){
        RestAssured.baseURI="https://postman-echo.com";
        String payload="<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Including External JavaScript File</title>        \n" +
                "</head>\n" +
                "<body>    \n" +
                "    <button type=\"button\" id=\"myBtn\">Click Me</button>\n" +
                "    <script src=\"js/hello.js\"></script>\n" +
                "</body>\n" +
                "</html>";

        given().log().all()
                .contentType(ContentType.HTML)
                .body(payload)
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithXMLTest(){
        RestAssured.baseURI="https://postman-echo.com";
        String payload="<user>\n" +
                "    <name>Ash B</name>\n" +
                "    <gender>female</gender>\n" +
                "    <email>ash.b@example.com</email>\n" +
                "    <status>active</status>\n" +
                "</user>";

        given().log().all()
                .contentType("application/json; charset=utf-8")
                .body(payload)
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithMultipartTest(){
        RestAssured.baseURI="https://postman-echo.com";

        given().log().all()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("resume", new File("./src/test/resources/multipart/abc.docx"))
                    .multiPart("name", "Ash B")
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithPDFFileTest(){
        RestAssured.baseURI="https://postman-echo.com";

        given().log().all()
                .contentType("application/pdf")
                .body(new File("./src/test/resources/multipart/abc.pdf"))
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithImageTest(){
        RestAssured.baseURI="https://postman-echo.com";

        given().log().all()
                .contentType("image/jpeg")
                .body(new File("./src/test/resources/multipart/image.jpg"))
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}

