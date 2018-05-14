package com.hotels.apps;

import com.hotels.apps.dao.UserDao;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class userDaoTest {

    private UserDao userDao = new UserDao();

    private final int EXISTING_USER_ID = 3;
    private final int NON_EXISTING_USER_ID = 9;

    @Test
    public void shouldReturnBookingsForExistingUser() {
        assertThat(Long.valueOf(userDao.getBookings(EXISTING_USER_ID)).intValue(), is(15));
    }

    @Test
    public void shouldReturnZeroBookingsForNonExistingUser() {
        assertThat(Long.valueOf(userDao.getBookings(NON_EXISTING_USER_ID)).intValue(), is(0));
    }


    @Test
    public void shouldReturnBookingsValueForExistingUser() {
        assertThat(userDao.getBookingsValue(EXISTING_USER_ID), is(6799.0));
    }

    @Test
    public void shouldReturnBookingsValueForNonExistingUser() {
        assertThat(userDao.getBookingsValue(NON_EXISTING_USER_ID), is(0.0));
    }

    @Test
    public void shouldReturnAverageStayLengthForExistingUser() {
        assertThat(userDao.getAverageStayLength(EXISTING_USER_ID), is(6.27));
    }

    @Test
    public void shouldReturnAverageStayLengthForNonExistingUser() {
        assertThat(userDao.getAverageStayLength(NON_EXISTING_USER_ID), is(0.0));
    }
}
