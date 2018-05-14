package com.hotels.apps.model;

import com.fasterxml.jackson.databind.JsonNode;

class UserFeatureValidator {

    static int validateUserId(JsonNode userIdNode) {
        return validateIntegerField(userIdNode);
    }

    static int validateHotelId(JsonNode hotelIdNode) {
        return validateIntegerField(hotelIdNode);
    }

    static int validateNumberOfNights(JsonNode numberOfNightsNode) {
        return validateIntegerField(numberOfNightsNode);
    }

    static double validateTotalPriceInUSD(JsonNode totalPriceNode) {

        if(totalPriceNode.asDouble() == 0.0) {
            throw new IllegalArgumentException(String.format("totalPriceInUSD has illegal format [%s]", totalPriceNode.toString()));
        }

        return totalPriceNode.asDouble();
    }

    private static int validateIntegerField(JsonNode userIdNode) {
        if(userIdNode.asInt() == 0) {
            throw new IllegalArgumentException(String.format("Field has illegal format [%s]", userIdNode.toString()));
        }

        return userIdNode.asInt();
    }
}
