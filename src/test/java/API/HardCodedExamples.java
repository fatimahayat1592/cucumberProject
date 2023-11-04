package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA5OTIyNjYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTAzNTQ2NiwidXNlcklkIjoiNTYxMiJ9.5DoI1_AeLmqfHIeK53sR4c0qsfBbrmU5W2C-AF-tBTA";
  static  String employee_id;
   @Test
    public  void acreateEmployee(){
        //prepare the request because restAssured follows BDD approach
      RequestSpecification request= given().header("Content-Type","application/json").
               header("Authorization", token).body("{\n" +
                       "  \"emp_firstname\": \"uzair\",\n" +
                       "  \"emp_lastname\": \"assam\",\n" +
                       "  \"emp_middle_name\": \"pr\",\n" +
                       "  \"emp_gender\": \"M\",\n" +
                       "  \"emp_birthday\": \"2010-07-23\",\n" +
                       "  \"emp_status\": \"happy\",\n" +
                       "  \"emp_job_title\": \"QA\"\n" +
                       "}");
      //hitting end point
     Response response= request.when().post("/createEmployee.php");
     //verification
        response.then().assertThat().statusCode(201);
        //for printing response
        response.prettyPrint();
        //verify firstname
       response.then().assertThat().body("Employee.emp_firstname", equalTo("uzair"));
       response.then().assertThat().body("Employee.emp_middle_name", equalTo("pr"));
       response.then().assertThat().body("Message", equalTo("Employee Created"));
       response.then().assertThat().header("X-Powered-By","PHP/7.2.18");
       employee_id=response.jsonPath().getString("Employee.employee_id");
       System.out.println(employee_id);
    }
    @Test
    public void bgetCreatedEmployee(){
       RequestSpecification request=given().header("Authorization",token).
               queryParam("employee_id",employee_id);
       Response response=request.when().get("/getOneEmployee.php");
       response.then().assertThat().statusCode(200);
       response.prettyPrint();
       String tempEmpID=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpID);
    }
    @Test
    public void cUpdateEmployee(){
       RequestSpecification request=given().header("Content-Type","application/json").
               header("Authorization", token).body("{\n" +
                       "  \"employee_id\": \""+employee_id+"\",\n" +
                       "  \"emp_firstname\": \"chi\",\n" +
                       "  \"emp_lastname\": \"la\",\n" +
                       "  \"emp_middle_name\": \"bb\",\n" +
                       "  \"emp_gender\": \"F\",\n" +
                       "  \"emp_birthday\": \"2011-07-29\",\n" +
                       "  \"emp_status\": \"path\",\n" +
                       "  \"emp_job_title\": \"eng\"\n" +
                       "}");
       Response response=request.when().put("/updateEmployee.php");
       response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message",
                equalTo("Employee record Updated"));
    }
    @Test
    public void dgetCreatedEmployee(){
        RequestSpecification request=given().header("Authorization",token).
                queryParam("employee_id",employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String tempEmpID=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpID);
    }
    @Test
    public void ePartiallyUpdateEmployee(){
       RequestSpecification request=given().header("Content-Type","application/json").
               header("Authorization", token).body("{\n" +
                       "  \"employee_id\": \""+employee_id+"\",\n" +
                       "  \"emp_middle_name\": \"aa\"\n" +
                       "}");
        Response response=request.when().patch("/updatePartialEmplyeesDetails.php");
        response.then().assertThat().statusCode(201);
        response.prettyPrint();
        response.then().assertThat().body("Message",
                equalTo("Employee record updated successfully"));
    }
    @Test
    public void fgetCreatedEmployee(){
        RequestSpecification request=given().header("Authorization",token).
                queryParam("employee_id",employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String tempEmpID=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpID);
    }
}
