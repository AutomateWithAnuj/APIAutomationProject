package com.AutomateWithAnuj.endpoints;

public class APIConstants {
    //it will keep our all the URLs because these will rarely be changed
    //also we created the static so that we can use them with only class name
    public static String BASE_URL = "https://restful-booker.herouapp.com";
    //public static String BASE_URL = //Fetch this from Excel file
    public static String CREATE_UPDATE_BOOKING_URL = "/booking";
    public static String AUTH_URL = "/auth";
    public static String PING_URL = "/ping";
}
