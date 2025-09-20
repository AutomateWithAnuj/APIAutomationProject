package com.AutomateWithAnuj.tests.e2e_Integerations;

import com.AutomateWithAnuj.Asserts.Asserts.AssertActions;
import com.AutomateWithAnuj.Base.BaseTest;
import com.AutomateWithAnuj.Pojos.Request.Booking;
import com.AutomateWithAnuj.Pojos.Response.BookingResponse;
import com.AutomateWithAnuj.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestIntegerationFlow2 extends BaseTest{
    //create Booking -> Delete it -> Verify
    @Test(groups = "qa",priority = 1)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        //Setup and Making a Request
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        Response response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).post();

        //Extraction Part
        BookingResponse bookingResponse = (BookingResponse) payloadManager.createPayloadBookingAsObject(response.asString());

        //Verification Part
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Anuj");
        assertActions.verifyStringKey(bookingResponse.getBooking().getLastname(),"Rajput");

        System.out.println(response.asString());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "qa",priority = 2)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 4. Delete the Booking By ID")
    public void testDeleteBookingId(ITestContext iTestContext){
        //here also we need to use the token do we have to create new
        //the answer is no we don't have to create the new token
        //just use that token itself which was created before and save it in iTestContext
        int bookingid = (int) iTestContext.getAttribute("bookingid");
        String token = getToken();
        if (token.equalsIgnoreCase(null)){
            token = getToken();
        }
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token",token);
        validatableResponse = RestAssured.given(requestSpecification).when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(groups = "qa",priority = 3)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 2. Verify the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId;
        requestSpecification.basePath(basePathGET);

        response =
                given(requestSpecification)
                        .when()
                        .get()
                        .then().log().all()
                        .extract().response();

        validatableResponse = response.then();
        validatableResponse.statusCode(404);
    }
}
