package APIStepDef;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
    String baseURI= RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
   public  static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
       //write code to generate token
        RequestSpecification request=given().header("Content-Type","application/json").
                body("{\n" +
                        "  \"email\": \"fatima@test.com\",\n" +
                        "  \"password\": \"fatima@123\"\n" +
                        "}");
        Response response=request.when().post("/generateToken.php");
       response.prettyPrint();
        token="Bearer "+ response.jsonPath().getString("token");
        System.out.println(token);
        response.then().assertThat().statusCode(200);
    }

}
