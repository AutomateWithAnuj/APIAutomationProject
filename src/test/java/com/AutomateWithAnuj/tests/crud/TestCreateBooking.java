package com.AutomateWithAnuj.tests.crud;

import com.AutomateWithAnuj.Base.BaseTest;
import com.AutomateWithAnuj.Pojos.Request.Booking;
import com.AutomateWithAnuj.Pojos.Request.Bookingdates;
import com.AutomateWithAnuj.Pojos.Response.BookingResponse;
import com.AutomateWithAnuj.endpoints.APIConstants;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {

    @Test(groups = "reg",priority = 1)
    @Owner("Anuj")
    @Description("TC#INT1 - Verify that the Booking can be Created")
    public void testCreateBookingPOST_POSITIVE(){

        //Setup and Making a Request
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).post();

        //Extraction Part
        BookingResponse bookingResponse = (BookingResponse) payloadManager.createPayloadBookingAsObject(response.asString());

        //Verification Part
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Anuj");
        assertActions.verifyStringKey(bookingResponse.getBooking().getLastname(),"Rajput");

        System.out.println(response.asString());
    }

    @Test(groups = "reg",priority = 1)
    @Owner("Anuj")
    @Description("TC#INT1 - Verify that the Booking can not be Created when payload is null")
    public void testCreateBookingPOST_POSITIVE_ChineseChars(){
        //Setup and Making a Request
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsStringChinese()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }

    @Test(groups = "reg",priority = 1)
    @Owner("Anuj")
    @Description("TC#INT1 - Verify that the Booking can not be Created when payload is null")
    public void testCreateBookingPOST_NEGATIVE(){
        //Setup and Making a Request
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body("{}").post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
    }

    @Test(groups = "reg",priority = 1)
    @Owner("Anuj")
    @Description("TC#INT1 - Verify that the Booking can not be Created when payload is null")
    public void testCreateBookingPOST_POSITIVE_RANDOMDATA(){
        //Setup and Making a Request
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString_Random_FakerJS()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
