import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CurrencyConversionsAPITest {


    @Test
    public void validAccessKeyTest() {
        Response response = given().get(Constants.URL + Constants.ACCESS_KEY);
        response.then().statusCode(200);
        System.out.println(response.asString());
    }

    @Test
    public void invalidAccessKeyTest() {
        Response response = given().get(Constants.URL + "ec26fddfd53247d31bdf1475cc266c0errrrr");
        response.then().body("error.code", equalTo(101));
        System.out.println(response.asString());
    }

    @Test
    public void missingAccessKey(){
        Response response = given().get(Constants.URL + "");
        response.then().body("type", containsString("missing_access_key"));
        System.out.println(response.asString());

    }

    @Test
    public void currentCurrencyConversionTest() {
        Response response = given().get(Constants.URL + Constants.ACCESS_KEY + Constants.CURRENCIES);
        System.out.println(response.asString());
    }

    @Test
    public void historicalDateTest() {
        DateFormat format = new SimpleDateFormat("2018-01-01");
        Response response = given().get(Constants.HISTORICAL_URL + format);
        response.then().statusCode(200);
        System.out.println(response.asString());
    }

    @Test
    public void incorrectFormatDateTest() {
        DateFormat format = new SimpleDateFormat("01-01-2018");
        Response response = given().get(Constants.HISTORICAL_URL + Constants.ACCESS_KEY + format);
        response.then().body("error.code", equalTo(302));
        System.out.println(response.asString());
    }

    @Test
    public void emptyDateTest() {
        DateFormat format = new SimpleDateFormat("");
        Response response = given().get(Constants.HISTORICAL_URL + Constants.ACCESS_KEY + format);
        response.then().body("error.code", equalTo(301));
        System.out.println(response.asString());
    }

    @Test
    public void incorrectCurrencyNameTest(){
        Response response = given().get(Constants.URL + Constants.ACCESS_KEY + "&currencies=CAN");
        response.then().body("error.code", equalTo(202));
        System.out.println(response.asString());
    }



}
