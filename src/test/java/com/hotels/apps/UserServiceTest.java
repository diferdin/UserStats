package com.hotels.apps;

import com.hotels.apps.dao.UserDao;
import com.hotels.apps.service.UserService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private final UserDao userDao = mock(UserDao.class);
    private final UserService underTest = new UserService(userDao);

    private final int USER_ID = 6;

    @Test
    public void shouldDelegateToUserDaoWhenCheckingUser() {
        underTest.userExists(USER_ID);
        verify(userDao, times(1)).userExists(USER_ID);
    }

    @Test
    public void shouldDelegateToUserDaoWhenGettingBookings() {
        underTest.getBookings(USER_ID);
        verify(userDao, times(1)).getBookings(USER_ID);
    }

    @Test
    public void shouldDelegateToUserDaoWhenGettingBookingsValue() {
        underTest.getBookingsValue(USER_ID);
        verify(userDao, times(1)).getBookingsValue(USER_ID);
    }

    @Test
    public void shouldDelegateToUserDaoWhenGettingAverageStayLength() {
        underTest.getAverageStayLength(USER_ID);
        verify(userDao, times(1)).getAverageStayLength(USER_ID);
    }
}
