package com.hotels.apps.module;

import com.google.inject.*;
import com.hotels.apps.dao.UserDao;
import com.hotels.apps.service.UserService;

public class ServiceModule extends AbstractModule {

    @Override
    public void configure() {

    }

    @Provides
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }

    @Provides
    public UserDao userDao() {
        return new UserDao();
    }
}
