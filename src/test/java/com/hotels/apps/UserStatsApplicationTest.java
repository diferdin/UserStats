package com.hotels.apps;

import com.hotels.apps.config.UserStatsConfiguration;
import io.dropwizard.setup.Environment;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UserStatsApplicationTest {

    private Environment environment = Mockito.mock(Environment.class);

    @Test(expected = RuntimeException.class)
    public void shouldFailWhenExceptionThrownDuringSetup() {
        UserStatsApplication application = new UserStatsApplication();

        when(environment.jersey()).thenThrow(RuntimeException.class);
        when(environment.healthChecks()).thenThrow(RuntimeException.class);

        application.run(new UserStatsConfiguration(), environment);
    }
}
