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

import static io.restassured.RestAssured.basePath;
import static org.assertj.core.api.Assertions.*;

import static io.restassured.RestAssured.given;

public class TestIntegerationFlow1 extends BaseTest {
    //TestE2EFlow_01

    // Test E2E Scenario 1

    // 1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, booking


    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Test(groups = "qa",priority = 1)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        //Setup and Making a Request
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).post();

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
        validatableResponse.statusCode(200);

        //when we are doing the get request we are getting the booking not booking response
        //post is giving us the booking response and others like get/put are giving the booking only
        Booking booking = (Booking) payloadManager.createPayloadBookingAsObject2(response.asString());
        assertActions.verifyStringKeyNotNull(booking.getFirstname());
    }

    @Test(groups = "qa",priority = 3)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 3. Update the Booking By ID")
    public void testUpdateBookingId(ITestContext iTestContext){
        //we can also put the booking ID also into the Base Test
        //because in some integration scenarios we need to do the creation booing 100 times
        //we can put it in base test and reuse it
        //Real reason of not putting is that the booking Id is manipulative(controlling)
        //so in this integration file we are manipulating the booking ID
        //that is not the case with the token we are not manipulating it
        // so we can put the token in the base test and not the booking ID
        //update and deletion of the booking I'd makes it manipulative, and we cannot put in bas test
        int bookingid = (int) iTestContext.getAttribute("bookingid");
        //Now we need the token,
        //token is common for each test case so we can put it inside BaseTest
        //or use the iTestContext in its method where we have that method
        //more preferred is the BaseTest Because we want to run it each time and single token is required
        //token is not updated or deleted so we can use the token,
        //token is like a universal thing for the test case
        String token = getToken();
        iTestContext.setAttribute("token",token);

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured.given(requestSpecification).cookie("token",token).when().body(payloadManager.createPayloadBookingAsString()).put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = (Booking) payloadManager.createPayloadBookingAsObject2(response.asString());

        assertActions.verifyStringKeyNotNull(booking.getFirstname());
    }

    @Test(groups = "qa",priority = 4)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 4. Delete the Booking By ID")
    public void testDeleteBookingId(ITestContext iTestContext){
        //here also we need to use the token do we have to create new
        //the answer is no we don't have to create the new token
        //just use that token itself which was created before and save it in iTestContext
        int bookingid = (int) iTestContext.getAttribute("bookingid");
        String token = iTestContext.getAttribute("token").toString();
        if (token.equalsIgnoreCase(null)){
            token = getToken();
        }
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token",token);
        validatableResponse = RestAssured.given(requestSpecification).when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

}
