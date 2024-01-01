import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import HW3.weather.Weather;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ForecastsTest extends AccuweatherAbstractTest{

    @Test
    void testGetResponse1Day() {
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{locationKey}")
                .then().statusCode(200).time(lessThan(2000L))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals(1, weather.getDailyForecasts().size());
    }

    @Test
    void testGetResponse10Day() {
        String code = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}")
                .then().statusCode(401)
                .extract().jsonPath()
                .getString("Code");
        Assertions.assertAll(() -> Assertions.assertEquals("Unauthorized", code));
        System.out.println(code);
    }

    @Test
    void testGetResponse15Day() {
        String code = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/15day/{locationKey}")
                .then().statusCode(401)
                .extract().jsonPath()
                .getString("Code");
        Assertions.assertAll(() -> Assertions.assertEquals("Unauthorized", code));
        System.out.println(code);
    }


}
