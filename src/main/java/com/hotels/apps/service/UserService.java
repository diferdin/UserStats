package com.hotels.apps.service;

import com.hotels.apps.dao.UserDao;

import javax.inject.Inject;

public class UserService {

    private final UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean userExists(int userId) {
        return userDao.userExists(userId);
    }

    public int getBookings(int userId) {
        return Long.valueOf(userDao.getBookings(userId)).intValue();
    }

    public double getBookingsValue(int userId) {
        return userDao.getBookingsValue(userId);
    }

    public double getAverageStayLength(int userId) {
        return userDao.getAverageStayLength(userId);
    }
}
