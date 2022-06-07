import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class GetBookByIDTest {

    private static Response response;

    @ParameterizedTest
    @ValueSource(strings={"5cf5805fb53e011a64671582","5cf58077b53e011a64671583","5cf58080b53e011a64671584"})
    public void getBooksByIdResultTest(String bookId){
        response = given().get(Constants.URL+Constants.BOOKS_ENDPOINT + "/" + bookId);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("total",equalTo(1));
        response.then().body("docs._id", contains(bookId));
        response.then().body("docs.name", notNullValue());
    }
}
