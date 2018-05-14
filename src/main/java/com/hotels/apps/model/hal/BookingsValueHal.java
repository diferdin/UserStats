package com.hotels.apps.model.hal;

import lombok.Getter;

import javax.ws.rs.core.UriInfo;

@Getter
public class BookingsValueHal extends HalWrapper {

    private final double value;

    public BookingsValueHal(double value, UriInfo uriInfo) {
        super(uriInfo);
        this.value = value;
    }

}
