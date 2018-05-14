package com.hotels.apps.model.hal;

import lombok.Getter;

import javax.ws.rs.core.UriInfo;

@Getter
public class UserBookingsHal extends HalWrapper {

    private final int bookings;

    public UserBookingsHal(int bookings, UriInfo uriInfo) {
        super(uriInfo);
        this.bookings = bookings;
    }

}
