package com.AutomateWithAnuj.tests.e2e_Integerations;

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

        Response response =
                given(requestSpecification)
                        .when()
                        .get()
                        .then().log().all()
                        .extract().response();

        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        Booking booking = (Booking) payloadManager.createPayloadBookingAsObject(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Pramod");
    }

    @Test(groups = "qa",priority = 3)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 3. Update the Booking By ID")
    public void testUpdateBookingId(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 4)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 4. Delete the Booking By ID")
    public void testDeleteBookingId(){
        Assert.assertTrue(true);
    }

}
