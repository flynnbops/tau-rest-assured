package org.example;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Chapter2 {

  @Test
  public void requestUsZipCode90210_checkStatus_expect200() {
    given().
    when().
      get("http://zippopotam.us/us/90210").
    then().
      assertThat().
            statusCode(200);
  }

  @Test
  public void requestUsZipCode90210_checkContentType_expectJSON() {
    given().
    when().
      get("http://zippopotam.us/us/90210").
    then().
      assertThat().
      contentType(ContentType.JSON);
  }

  @Test
  public void requestUsZipCode90210_logRequestAndResponseDetails() {
    given().
      log().all().
    when().
      get("http://zippopotam.us/us/90210").
    then().
      log().all();
  }

  @Test
  public void requestUsZipCode90210_logResponseBody() {
    given().
    when().
      get("http://zippopotam.us/us/90210").
    then().
      log().body();
  }

  @Test
  public void requestUsZipCode90210_checkPostCode_expect90210() {
    given().
    when().
      get("http://zippopotam.us/us/90210").
    then().
      assertThat().
      body("'post code'",equalTo("90210"));
  }

  @Test
  public void requestUsZipCode90210_checkPlaceName_expectBeverlyHills() {
    given().
            when().
            get("http://zippopotam.us/us/90210").
            then().
            assertThat().
            body("places[0].'place name'",equalTo("Beverly Hills"));
  }

  @Test
  public void requestUsZipCode90210_checkPlacesListSize_expectOne() {
    given().
    when().
      get("http://zippopotam.us/us/90210").
    then().
      assertThat().
      body("places",hasSize(1));
  }
}
