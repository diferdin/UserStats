package com.hotels.apps.resource;

import com.hotels.apps.model.hal.AverageStayLengthHal;
import com.hotels.apps.model.hal.BookingsValueHal;
import com.hotels.apps.model.hal.UserBookingsHal;
import com.hotels.apps.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("{userId}/bookings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookings(@PathParam("userId") int userId, @Context UriInfo uriInfo) {

        if (userIsInvalid(userId)) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(new UserBookingsHal(userService.getBookings(userId), uriInfo)).build();
    }

    @GET
    @Path("{userId}/bookings/value")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookingsValue(@PathParam("userId") int userId, @Context UriInfo uriInfo) {
        if (userIsInvalid(userId)) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(new BookingsValueHal(userService.getBookingsValue(userId), uriInfo)).build();
    }

    @GET
    @Path("{userId}/bookings/average-stay")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvgStayLength(@PathParam("userId") int userId, @Context UriInfo uriInfo) {
        if (userIsInvalid(userId)) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(new AverageStayLengthHal(userService.getAverageStayLength(userId), uriInfo)).build();
    }

    private boolean userIdExists(int userId) {
        return userService.userExists(userId);
    }

    private boolean userIsInvalid(int userId) {
        return !userIdExists(userId);
    }
}
