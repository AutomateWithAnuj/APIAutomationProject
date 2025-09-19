package com.AutomateWithAnuj.Asserts.Asserts;

import io.restassured.response.Response;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {
    public void verifyResponseBody(String actual,String expected,String description){
        Assert.assertEquals(actual, expected, description);
    }
    public void verifyResponseBody(int actual,int expected,String description){
        Assert.assertEquals(actual, expected, description);
    }
    public void verifyStatusCode(Response response, Integer expected){
        Assert.assertEquals(response.getStatusCode(), expected);
    }
    public void verifyStringKey(String keyExcept,String keyActual){
        //AssertJ
        assertThat(keyExcept).isNotNull().isNotBlank();
        assertThat(keyExcept).isEqualTo(keyActual);
    }
    public void verifyStringKeyNotNull(Integer KeyExpect){
        assertThat(KeyExpect).isNotNull();
    }
    public void verifyStringKeyNotNull(String KeyExpect){
        assertThat(KeyExpect).isNotNull();
    }
}
