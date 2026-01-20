package POSTAPITestWithPOJOLombok;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestCreateUserUsingLombok {

    private String getRandomEmailID(){
        return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
    }

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI="https://gorest.co.in";
    }

    @Test
    public void createAUserUsingLombokTest(){
        UserLombok userLombok= new UserLombok("Ash B", "female", getRandomEmailID(), "active");
        System.out.println(userLombok);

        //1. Create a user---POST -using pojo to json(serialization) using jackson(databind) lib
        Integer userID=given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(userLombok)
                .when()
                    .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                    .statusCode(201)
                .extract()
                    .path("id");
        System.out.println("New user id is:"+userID);

        System.out.println("=================================");

        //2.Get a user---GET
        given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                    .get("/public/v2/users/"+userID)
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body("id", equalTo(userID));
    }

    @Test
    public void createAUserUsingLombokBuilderTest(){
        UserLombok userLombokBuilder= new UserLombok.UserLombokBuilder()
                                            .name("Ash B").status("active").gender("female").email(getRandomEmailID()).build();
        System.out.println(userLombokBuilder);

        //1. Create a user---POST -using pojo to json(serialization) using jackson(databind) lib
        Integer userID=given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(userLombokBuilder)
                .when()
                    .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                    .statusCode(201)
                .extract()
                    .path("id");
        System.out.println("New user id is:"+userID);

        System.out.println("=================================");

        //2.Get a user---GET
        given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                    .get("/public/v2/users/"+userID)
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .and()
                    .body("id", equalTo(userID));
    }

    @Test
    public void createAUserUsingLombokBuilderWithGetterTest(){
        UserLombok userLombokBuilder= new UserLombok.UserLombokBuilder()
                .name("Ash B").status("active").gender("female").email(getRandomEmailID()).build();
        System.out.println(userLombokBuilder);

        //1. Create a user---POST -using pojo to json(serialization) using jackson(databind) lib
        Response response=given().log().all()
                    .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                    .contentType(ContentType.JSON)
                    .body(userLombokBuilder)
                .when()
                    .post("/public/v2/users");

        response.prettyPrint();
        JsonPath jsonPath= response.jsonPath();
        int userID=jsonPath.getInt("id");
        System.out.println("New user id is:"+userID);

        Assert.assertEquals(jsonPath.getString("name"), userLombokBuilder.getName());
        Assert.assertEquals(jsonPath.getString("gender"), userLombokBuilder.getGender());
        Assert.assertEquals(jsonPath.getString("status"), userLombokBuilder.getStatus());
        Assert.assertEquals(jsonPath.getString("email"), userLombokBuilder.getEmail());
        Assert.assertNotNull(userID);

        System.out.println("=================================");

        /*//2.Get a user---GET
        given().log().all()
                .header("Authorization", "Bearer b949db127e312a464aef7af4e192be1c0d1649b475f0b9c17e89e02d50b9fcf5")
                .when()
                .get("/public/v2/users/"+userID)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", equalTo(userID));*/
    }
}
