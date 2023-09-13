package org.example;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class Chapter1 {

  @Test
  public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {
    given().
    when().
      get("http://zippopotam.us/us/90210").
    then().
      assertThat().
            statusCode(200).
            body("places[0].'place name'", equalTo("Beverly Hills"));
  }
}
