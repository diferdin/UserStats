package com.hotels.apps;

import com.hotels.apps.model.hal.AverageStayLengthHal;
import com.hotels.apps.model.hal.BookingsValueHal;
import com.hotels.apps.model.hal.UserBookingsHal;
import com.hotels.apps.resource.UserResource;
import com.hotels.apps.service.UserService;
import com.squarespace.jersey2.guice.JerseyGuiceUtils;
import org.junit.Before;
import org.junit.Test;
import testsupport.FakeUriInfo;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UserResourceTest {

    private final UserService userService = mock(UserService.class);
    private final UserResource underTest = new UserResource(userService);
    private final int USER_ID = 6;

    @Before
    public void setup() {
        JerseyGuiceUtils.install((s, serviceLocator) -> null);
    }

    @Test
    public void shouldReturnBookingHal() {

        when(userService.userExists(USER_ID)).thenReturn(true);

        Response response = underTest.getBookings(USER_ID, new FakeUriInfo());
        assertThat(response.getEntity(), instanceOf(UserBookingsHal.class));
    }

    @Test
    public void shouldReturnNumberOfBookingsWithinHal() {

        when(userService.userExists(USER_ID)).thenReturn(true);
        when(userService.getBookings(USER_ID)).thenReturn(12);

        Response response = underTest.getBookings(USER_ID, new FakeUriInfo());

        UserBookingsHal bookingsHal = (UserBookingsHal)response.getEntity();
        assertThat(bookingsHal.getBookings(), is(12));
    }

    @Test
    public void shouldReturnBookingsValueWithinHal() {

        when(userService.userExists(USER_ID)).thenReturn(true);
        when(userService.getBookingsValue(USER_ID)).thenReturn(12.00);

        Response response = underTest.getBookingsValue(USER_ID, new FakeUriInfo());

        BookingsValueHal bookingsValueHal = (BookingsValueHal)response.getEntity();
        assertThat(bookingsValueHal.getValue(), is(12.00));
    }

    @Test
    public void shouldReturnAverageStayLengthWithinHal() {

        when(userService.userExists(USER_ID)).thenReturn(true);
        when(userService.getAverageStayLength(USER_ID)).thenReturn(12.00);

        Response response = underTest.getAvgStayLength(USER_ID, new FakeUriInfo());

        AverageStayLengthHal averageStayLengthHal = (AverageStayLengthHal)response.getEntity();
        assertThat(averageStayLengthHal.getAverageStayLength(), is(12.00));
    }

    @Test
    public void shouldReturnNotFoundForUnknownUserId() {
        Response response = underTest.getBookings(USER_ID, new FakeUriInfo());
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void shouldDelegateBookingsToUserService() {
        underTest.getBookings(USER_ID, new FakeUriInfo());
        verify(userService, times(1)).userExists(USER_ID);
    }
}
