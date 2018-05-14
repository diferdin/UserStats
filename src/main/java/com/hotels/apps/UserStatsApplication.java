package com.hotels.apps;

import com.hotels.apps.config.UserStatsConfiguration;
import com.hotels.apps.dao.UserDao;
import com.hotels.apps.healthcheck.UserStatsHealthCheck;
import com.hotels.apps.module.ServiceModule;
import com.hotels.apps.resource.UserResource;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class UserStatsApplication extends Application<UserStatsConfiguration> {

    @Override
    public void initialize(Bootstrap<UserStatsConfiguration> bootstrap) {
        GuiceBundle<UserStatsConfiguration> guiceBundle = GuiceBundle.<UserStatsConfiguration>newBuilder()
                .addModule(new ServiceModule())
                .setConfigClass(UserStatsConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    public static void main(String... args) throws Exception {

        new UserStatsApplication().run(args);
    }

    @Override
    public String getName() {
        return "user-stats-app";
    }


    @Override
    public void run(UserStatsConfiguration userStatsConfiguration, Environment environment) {

        environment.healthChecks().register("user-stats-app", new UserStatsHealthCheck());
        environment.jersey().register(new ServiceModule());
        environment.jersey().register(UserResource.class);

        new UserDao();
    }
}
