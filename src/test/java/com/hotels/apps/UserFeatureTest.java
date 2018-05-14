package com.hotels.apps;

import com.hotels.apps.model.UserFeature;
import org.junit.Test;

public class UserFeatureTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserIdIsIncorrect() {

        String jsonString = "{\"userId\":\"blah\", \"hotelId\":\"66\", \"numberOfNights\":4, \"totalPriceInUSD\":428}\n";

        new UserFeature(jsonString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfHotelIdIsIncorrect() {

        String jsonString = "{\"userId\":\"1\", \"hotelId\":\"foobar\", \"numberOfNights\":4, \"totalPriceInUSD\":428}\n";

        new UserFeature(jsonString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNumberOfNightsIsIncorrect() {

        String jsonString = "{\"userId\":\"1\", \"hotelId\":\"2\", \"numberOfNights\":bang, \"totalPriceInUSD\":428}\n";

        new UserFeature(jsonString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPriceInUSDIsIncorrect() {

        String jsonString = "{\"userId\":\"1\", \"hotelId\":\"2\", \"numberOfNights\":2, \"totalPriceInUSD\":zero}\n";

        new UserFeature(jsonString);
    }

}
