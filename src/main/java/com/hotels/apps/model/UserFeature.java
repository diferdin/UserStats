package com.hotels.apps.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;

import static com.hotels.apps.model.UserFeatureValidator.*;

@Getter
public class UserFeature implements Comparable<UserFeature>{

    private final int userId;
    private final int hotelId;
    private final int numberOfNights;
    private final double totalPriceInUSD;

    public UserFeature(String jsonString) {

        try {
            JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

            this.userId = validateUserId(jsonNode.get("userId"));
            this.hotelId = validateHotelId(jsonNode.get("hotelId"));
            this.numberOfNights = validateNumberOfNights(jsonNode.get("numberOfNights"));
            this.totalPriceInUSD = validateTotalPriceInUSD(jsonNode.get("totalPriceInUSD"));

        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("%s has illegal format", jsonString));
        }
    }

    @Override
    public int compareTo(UserFeature feature) {

        return Integer.compare(this.getUserId(), feature.getUserId());

    }
}
