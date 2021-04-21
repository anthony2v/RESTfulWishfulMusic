package com.spicecrispies.service;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
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
        AuthenticationREST.tokenHeader = requestContext.getHeaderString("Authorization-Token");

        System.out.println("token Header in filter: "+ AuthenticationREST.tokenHeader);
        Date generatedTime=AuthenticationREST.tokenExpiration.get(AuthenticationREST.tokenHeader);

        if (generatedTime == null) {
            System.out.println("Token in Header is NOT VALID");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated by Request Filter!").build());
            return ;
        }
        else // If the token is not changed
        {
            Date timeNow = new Date();
            long diff = timeNow.getTime() - generatedTime.getTime() ;
            long tokenDuration = TimeUnit.MILLISECONDS.toMinutes(diff);
            System.out.println("Duration: " + tokenDuration);

            if (tokenDuration > 30){
                AuthenticationREST.tokenUsername.remove(AuthenticationREST.tokenHeader);
                AuthenticationREST.tokenExpiration.remove(AuthenticationREST.tokenHeader);
                System.out.println("Session timed out");
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated by Filter!").build());
                return;
            }
            else{
                System.out.println("Token in Header is VALID");
                return;

            }
        }

    }
}