package com.spicecrispies.service;



import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class ResponseFilter implements ContainerResponseFilter{
    @Override
    public void filter(ContainerRequestContext requestContext,ContainerResponseContext responseContext)
            throws IOException {

        responseContext.getHeaders().add("X-Powered-By", "RESTfulMusic");
        UriInfo uriInfo=requestContext.getUriInfo();
        if (uriInfo.getPath().contains("user/register") || uriInfo.getPath().contains("user/login") || uriInfo.getPath().contains("user/logout") ) {
            return ;
        }

        responseContext.getHeaders().add("Authorization-Token", AuthenticationREST.tokenHeader);
    }
}
