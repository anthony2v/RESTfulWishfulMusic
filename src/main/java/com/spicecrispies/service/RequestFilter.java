package com.spicecrispies.service;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;


import java.util.Date;
import java.util.concurrent.TimeUnit;

@Provider
public class RequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        UriInfo uriInfo=requestContext.getUriInfo();
        if (uriInfo.getPath().contains("user/register") || uriInfo.getPath().contains("user/login") || uriInfo.getPath().contains("user/logout") ) {
            return ;
        }
        UserRest.tokenHeader = requestContext.getHeaderString("Authorization_Token");
        System.out.println("The Token Header in Filter is  "+UserRest.tokenHeader);
        Date generatedTime=UserRest.tokenExpiration.get(UserRest.tokenHeader);


        if (generatedTime == null) {
            System.out.println("Token in Header is NOT VALID");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated by RequestFilter Class!").build());
            return ;

        }
        else {
            if (generatedTime != null) {
                Date timeNow = new Date();
                long difference = timeNow.getTime() - generatedTime.getTime();
                long tokenDuration = TimeUnit.MILLISECONDS.toMinutes(difference);
                System.out.println("Duration: " + tokenDuration);

                if (tokenDuration > 30) {
                    UserRest.token_name.remove(UserRest.tokenHeader);
                    UserRest.tokenExpiration.remove(UserRest.tokenHeader);
                    System.out.println("Session timed out. Try again!");
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated by RequestFilter Class!").build());
                    return;
                } else {
                    System.out.println("Token in Header is VALID");
                    System.out.println("The Token Header in Filter is  "+ UserRest.tokenHeader + "at time " + generatedTime);
                    return;
                }
            }
        }
    }
}