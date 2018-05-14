package com.hotels.apps.dao;

import com.hotels.apps.model.UserFeature;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class UserDao {

    private List<UserFeature> userFeatures;

    public UserDao() {
        userFeatures = loadDataFromFile();

        if(userFeatures.isEmpty()) {
            throw new IllegalArgumentException("Data could not be loaded from file");
        }
    }

    private List<UserFeature> loadDataFromFile() {
        String USER_FEATURES_FILENAME = "user_features.txt";
        List<UserFeature> loadedData = new ArrayList<>();


        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(USER_FEATURES_FILENAME)));
        String line;

        try {

            while ((line = reader.readLine()) != null) {
                loadedData.add(new UserFeature(line));
            }

            Collections.sort(loadedData);

            return loadedData;
        } catch(IOException ioe) {

            System.out.println(ioe.getMessage());

        }

        return Collections.emptyList();
    }

    public boolean userExists(int userId) {
        return userFeatures.stream().anyMatch( userFeature -> Integer.valueOf(userFeature.getUserId()).equals(userId));
    }

    public long getBookings(int userId) {
        return userFeatures.stream().filter(user -> user.getUserId() == userId).count();
    }

    public double getBookingsValue(int userId) {
        return userFeatures.stream().filter(user -> user.getUserId() == userId).mapToDouble(UserFeature::getTotalPriceInUSD).sum();
    }

    public double getAverageStayLength(int userId) {
        double averageStay =  userFeatures.stream().filter(user -> user.getUserId() == userId).mapToDouble(UserFeature::getNumberOfNights).average().orElse(0.00);
        return round(averageStay);
    }

    private double round(double originalFigure) {
        DecimalFormat doubleFormatter = new DecimalFormat("0.00");
        return Double.parseDouble(doubleFormatter.format(originalFigure));
    }
}
