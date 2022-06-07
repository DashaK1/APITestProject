import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateEmployeeTest {

    public static final String EMPLOYEE_NAME = "name";
    public static final String EMPLOYEE_AGE = "age";
    public static final String EMPLOYEE_SALARY = "salary";

    @Test
    public void createEmployeeTest() {
        Response response = given().contentType("application/json").body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}").post(Constants.URL_EMPLOYEES + Constants.CREATE_EMPLOYEE);
        System.out.println(response.asString());

        response.then().body("data.name", equalTo("test"));
        response.then().body("message", containsString("Successfully!"));
    }

    @Test
    public void createEmployeeTestHashMapExample() {
        HashMap employee = new HashMap();
        employee.put(EMPLOYEE_NAME,"Vasya Pupkin");
        employee.put(EMPLOYEE_AGE,"90");
        employee.put(EMPLOYEE_SALARY,"10000");
        Response response = given().contentType("application/json").body(employee).post(Constants.URL_EMPLOYEES + Constants.CREATE_EMPLOYEE);
        System.out.println(response.asString());

        response.then().body("data.name", equalTo(employee.get(EMPLOYEE_NAME)));
        response.then().body("data.age", equalTo(employee.get(EMPLOYEE_AGE)));
        response.then().body("data.salary", equalTo(employee.get(EMPLOYEE_SALARY)));
        response.then().body("data.id", notNullValue());

    }
}
