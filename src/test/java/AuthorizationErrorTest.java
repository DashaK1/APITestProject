import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthorizationErrorTest {

    @Test
    public void invalidTokenTest(){
        Response response = given().auth().oauth2("blabla").contentType("application/json").get(Constants.URL+Constants.MOVIES_ENDPOINT);
        System.out.println(response.asString());
        response.then().statusCode(401);
        //containsString if error message different id or name or text part
        response.then().body("message", equalTo("Unauthorized."));
   }
}
