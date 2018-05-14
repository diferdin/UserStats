package com.hotels.apps.model.hal;

import lombok.Getter;

import javax.ws.rs.core.UriInfo;

@Getter
public class AverageStayLengthHal extends HalWrapper {

    private double averageStayLength;

    public AverageStayLengthHal(double averageStayLength, UriInfo uriInfo) {
        super(uriInfo);
        this.averageStayLength = averageStayLength;
    }

}
