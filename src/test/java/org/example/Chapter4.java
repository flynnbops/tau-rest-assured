package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Chapter4 {

  private static RequestSpecification requestSpec;
  private static ResponseSpecification responseSpec;

  @BeforeClass
  public static void createRequestSpecification() {
    requestSpec = new RequestSpecBuilder().
            setBaseUri("http://zippopotam.us/").
            setContentType(ContentType.JSON).
            build();
  }

  @BeforeClass
  public static void createResponseSpecification() {
    responseSpec = new ResponseSpecBuilder().
            expectStatusCode(200).
            expectContentType(ContentType.JSON).
            build();
  }


  @Test
  public void requestUsZipCode90210_checkStatusCode_expect200() {
    given().
      spec(requestSpec).
    when().
      get("us/90210").
    then().
      spec(responseSpec).
      and().
    assertThat().
      body("places[0].'place name'", equalTo("Beverly Hills"));
  }

  @Test
  public void requestUsZipCode90210_extractPlaceName_expectBeverlyHills() {
    // Extract the place name from the response body
    String placeName =
    given().
      spec(requestSpec).
    when().
      get("us/90210").
    then().
      spec(responseSpec).
    and().
      extract().
      path("places[0].'place name'");

    // Assert against the extracted value.
    Assert.assertEquals(placeName, "Beverly Hills");
  }
}
