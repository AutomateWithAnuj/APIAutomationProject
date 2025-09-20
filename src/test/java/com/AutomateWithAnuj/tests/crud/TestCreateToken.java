package com.AutomateWithAnuj.tests.crud;

import com.AutomateWithAnuj.Base.BaseTest;
import com.AutomateWithAnuj.Pojos.Request.Auth;
import com.AutomateWithAnuj.Pojos.Response.BookingResponse;
import com.AutomateWithAnuj.Pojos.Response.TokenResponse;
import com.AutomateWithAnuj.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Test(groups = "reg",priority = 1)
    @TmsLink("")
    @Owner("Anuj")
    @Description("TC#2 - Create Token and verify")
    public void testTokenPOST(){
        //Preparation of the request
        requestSpecification.basePath(APIConstants.AUTH_URL);

        //Making of the Request
        response = RestAssured.given(requestSpecification).when().body(payloadManager.setAuthPayload()).post();

        //Extraction Part (JSON String response to Java Object)
        String token = payloadManager.getTokenResponseString(response.asString());

        //Validation of the Request
        assertActions.verifyStringKeyNotNull(token);
        System.out.println(response.asString());


    }
}
