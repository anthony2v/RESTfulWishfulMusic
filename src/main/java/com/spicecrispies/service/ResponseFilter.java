package com.spicecrispies.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;

@Provider
public class ResponseFilter implements ContainerResponseFilter {
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        UriInfo uriInfo = requestContext.getUriInfo();
        if (uriInfo.getPath().contains("user/register") || uriInfo.getPath().contains("user/login") || uriInfo.getPath().contains("user/logout")) {
            return;
        }
        if (uriInfo.getPath().contains("album") && requestContext.getMethod().equals("POST") || requestContext.getMethod().equals("DELETE")) {
            String authorizationHeaderToken = requestContext.getHeaderString("x-api-key");
            if (!validateToken(authorizationHeaderToken) || authorizationHeaderToken == null || authorizationHeaderToken.equals("")) {
                responseContext.setEntity("ACCESS DENIED");
            }
            else if (requestContext.getMethod().equals("POST")) {
                responseContext.setEntity("Album added to wishlist of " + getUserFromToken(authorizationHeaderToken) + " at " + new Date());
            }
        }
    }

    private boolean validateToken(String token) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("%suser/authentication", Main.BASE_URI));
            httpPost.addHeader("x-api-key", token);
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String isAuthenticated = EntityUtils.toString(entity);
            httpResponse.close();
            if(isAuthenticated.equals("true")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getUserFromToken(String token) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(String.format("%suser", Main.BASE_URI));
            httpGet.addHeader("x-api-key", token);
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String username = EntityUtils.toString(entity);
            httpResponse.close();
            return username;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
