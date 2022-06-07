import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AllBooksEndpointTest {

    private static Response response;

    @BeforeAll
    public static void setUp(){
       response = given().get(Constants.URL+Constants.BOOKS_ENDPOINT);
        System.out.println(response.asString());
    }


    @Test
    public void getAllBooksResponseCodeTest(){
        response.then().statusCode(200);
    }

    @Test
    public void getAllBooksNumOfResultsTest(){
        response.then().body("total",notNullValue());
        response.then().body("total",greaterThan(0));
        response.then().body("total",equalTo(3));
    }

    @Test
    public void getAllBooksResultsHasItemsTest(){
        response.then().body("docs._id", notNullValue());
        response.then().body("docs._id", hasItem("5cf5805fb53e011a64671582"));
        response.then().body("docs.name", hasItem("The Fellowship Of The Ring"));

        response.then().body("docs.name", hasItems("The Fellowship Of The Ring","The Two Towers","The Return Of The King"));
    }

    @Test
    public void getAllBooksResultsContainsTest(){
        response.then().body("docs.name", contains("The Fellowship Of The Ring","The Two Towers","The Return Of The King"));
    }

    @Test
    public void getAllBooksPerformanceTest(){
        response.then().time(lessThan(2000l));

    }
}
