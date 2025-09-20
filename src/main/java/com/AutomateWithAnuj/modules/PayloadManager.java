package com.AutomateWithAnuj.modules;

import com.AutomateWithAnuj.Pojos.Request.Auth;
import com.AutomateWithAnuj.Pojos.Request.Booking;
import com.AutomateWithAnuj.Pojos.Request.Bookingdates;
import com.AutomateWithAnuj.Pojos.Response.BookingResponse;
import com.AutomateWithAnuj.Pojos.Response.TokenResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

public class PayloadManager {
    //convert the java object into the JSON String to use it as Request Payload.
    //Serialization
    public String createPayloadBookingAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Anuj"); //this payload can be changed and get from excel file as well
        booking.setLastname("Rajput");
        booking.setDepositpaid(true);
        booking.setTotalprice(112);
        booking.setAdditionalneeds("Breakfast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-09-18");
        bookingdates.setCheckout("2024-09-19");
        booking.setBookingdates(bookingdates);

        System.out.println(booking);

        //Java Object -> JSON
        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    public String createPayloadBookingAsStringChinese() {
        Booking booking = new Booking();
        booking.setFirstname("王伟"); //this payload can be changed and get from excel file as well
        booking.setLastname("李娜");
        booking.setDepositpaid(false);
        booking.setTotalprice(112);
        booking.setAdditionalneeds("张强");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("5025-12-18");
        bookingdates.setCheckout("5025-12-19");
        booking.setBookingdates(bookingdates);

        System.out.println(booking);

        //Java Object -> JSON
        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    //Convert the JSON String to Java Object so that we can verify response Body
    //DeSerialization
    public Object createPayloadBookingAsObject(String responseString) {
        Gson gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        Gson gson = new Gson();
        String jsonStringAuth = gson.toJson(auth);
        return jsonStringAuth;
    }

    public String getTokenResponseString(String TokenresponseString) {
        Gson gson = new Gson();
        TokenResponse tokenResponse = gson.fromJson(TokenresponseString, TokenResponse.class);
        return tokenResponse.getToken();
    }
    public String createPayloadBookingAsString_Random_FakerJS() {
        Faker faker = new Faker();

        Booking booking = new Booking();
        booking.setFirstname(String.valueOf(faker.name().firstName())); //this payload can be changed and get from excel file as well
        booking.setLastname(String.valueOf(faker.name().lastName()));
        booking.setDepositpaid(faker.random().nextBoolean());
        booking.setTotalprice(faker.random().nextInt(1,999));
        booking.setAdditionalneeds(String.valueOf(faker.food()));

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin(faker.date().toString());
        bookingdates.setCheckout(faker.date().toString());
        booking.setBookingdates(bookingdates);

        System.out.println(booking);

        //Java Object -> JSON
        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;

    }
    public Object createPayloadBookingAsObject2(String responseString) {
        Gson gson = new Gson();
        Booking booking = gson.fromJson(responseString, Booking.class);
        return booking;
    }

}