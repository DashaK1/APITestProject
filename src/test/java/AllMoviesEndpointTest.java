import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AllMoviesEndpointTest {
    private static Response response;

    @BeforeAll
    public static void setUp(){
        //response = given().auth().oauth2(Constants.TOKEN).get(Constants.URL+Constants.MOVIES_ENDPOINT);
        // we need send format (contentType)
        response = given().auth().oauth2(Constants.TOKEN).contentType("application/json").get(Constants.URL+Constants.MOVIES_ENDPOINT);
        System.out.println(response.asString());
    }

    @Test
    public void getAllMoviesResponseCodeTest(){
        response.then().statusCode(200);
    }
}
