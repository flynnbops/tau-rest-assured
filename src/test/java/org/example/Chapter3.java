package org.example;

import com.tngtech.java.junit.dataprovider.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


@RunWith(DataProviderRunner.class)
public class Chapter3 {

  @DataProvider
  public static Object[][] zipCodesAndPlaces(){
    return new Object[][] {
            {"us","90210", "Beverly Hills"},
            {"us","12345", "Schenectady"},
            {"ca","B2R", "Waverley"},
            {"ar","1601", "ISLA MARTIN GARCIA"}
    };
  }

  @Test
  @UseDataProvider("zipCodesAndPlaces")
  public void requestZipCodes_checkPlaceNameInBody_expectCorrectPlaceName(String countryCode, String zipCode, String placeName) {
    given().
      pathParams("countryCode",countryCode).pathParams("zipCode", zipCode).
    when().
      get("http://zippopotam.us/{countryCode}/{zipCode}").
    then().
      assertThat().
      statusCode(200).
      body("places[0].'place name'",equalTo(placeName));
  }

}
