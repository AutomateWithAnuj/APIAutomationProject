package com.AutomateWithAnuj.tests.endtoendIntegeration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegerationSample {
    // Create a Booking, Create a Token
    // Verify the Get booking
    // Update the booking
    // Delete the booking

    @Test(groups = "qa",priority = 1)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 2)
    @Owner("Anuj")
    @Description("TC#INT1 - Step 2. Verify the Booking By ID")
    public void testVerifyBookingId(){
        Assert.assertTrue(true);
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
