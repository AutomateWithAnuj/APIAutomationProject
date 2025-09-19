package com.AutomateWithAnuj.Base;

import com.AutomateWithAnuj.Asserts.Asserts.AssertActions;
import com.AutomateWithAnuj.endpoints.APIConstants;
import com.AutomateWithAnuj.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.AutomateWithAnuj.modules.PayloadManager;

public class BaseTest {
    // CommonToAll Testcase
    // Base URL, Content Type - json - common

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setup(){
        System.out.println("Starting of the Test");
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        /*requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL);
        requestSpecification.contentType(ContentType.JSON).log().all();*/

        //we can also use the RequestSpecBuilder class to run the above code
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();
    }

    @AfterTest
    public void tearDown(){
        System.out.println("Finished the Test!");
    }
}
