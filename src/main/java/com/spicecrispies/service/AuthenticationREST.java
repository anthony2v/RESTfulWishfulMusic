package com.spicecrispies.service;

import com.spicecrispies.core.entities.User;
import com.spicecrispies.persistence.UserMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Path("/user")
public class AuthenticationREST {
    private static Integer currentId = 0;
    private static final ArrayList<User> loggedInUsers = new ArrayList<>();
    private static final Map<String, String> tokenUsername = new HashMap<>();
    private static final Map<String, Date> tokenExpiration = new HashMap<>();

    @POST
    @Path("/register")
    public Response register(@FormParam("username") String username, @FormParam("password") String password) {
        User user = new User(currentId.toString(), username, password);
        currentId++;
        try {
            UserMapper.insert(user);
        } catch (ClassNotFoundException classNotFoundException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred registering the JDBC driver.").build();
        } catch (SQLException sqlException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred during insert query execution.").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred when trying to register new user: " + username + ".").build();
        }
        return Response.status(Response.Status.OK).entity("User Created: " + username).build();
    }


    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        User user;
        try {
            user = UserMapper.select(username);
        } catch (ClassNotFoundException classNotFoundException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred registering the JDBC driver.").build();
        } catch (SQLException sqlException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred during select query execution.").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred when trying to open session.").build();
        }
        boolean success = false;
        String token = "";
        Response.Status status;
        if (user != null) {
            if (user.getPassword().equals(password)) {
                user.generateToken();
                loggedInUsers.add(user);
                tokenUsername.put(user.getToken(), username);
                tokenExpiration.put(user.getToken(), new Date());
                success = true;
                token = user.getToken();
                status = Response.Status.OK;
            } else {
                status = Response.Status.UNAUTHORIZED;
            }
        } else {
            status = Response.Status.FORBIDDEN;
        }
        return Response.status(status).entity("Success: " + success + " and  Token: " + token).build();
    }

    @POST
    @Path("/logout")
    @Produces("application/json")
    public Response logout(@FormParam("username") String username) {
        User user = null;
        for (User loggedInUser : loggedInUsers) {
            if (loggedInUser.getName().equals(username))
                user = loggedInUser;
        }
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No user with the name " + username + " is logged in.").build();
        } else {
            tokenUsername.remove(user.getToken());
            tokenExpiration.remove(user.getToken());
            user.destroyToken();
            return Response.status(Response.Status.OK).entity("Logged out. Token successfully destroyed.").build();
        }
    }

    @POST
    @Path("/authentication")
    public Response validateToken(@HeaderParam("x-api-key") String token) {
        if (tokenUsername.containsKey(token)) {
            Date timeNow = new Date();
            long diff = timeNow.getTime() - tokenExpiration.get(token).getTime();
            long tokenDuration = TimeUnit.MILLISECONDS.toMinutes(diff);
            System.out.println("Duration: " + tokenDuration);
            if (tokenDuration > 30) {
                tokenUsername.remove(token);
                tokenExpiration.remove(token);
            } else {
                return Response.status(Response.Status.OK).entity("Token Correct").build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Token Invalid").build();
    }
}
