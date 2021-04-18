package com.spicecrispies.service;

import com.spicecrispies.core.entities.User;
import com.spicecrispies.core.entities.Response_two;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/user")
public class UserRest {
    public static String tokenHeader="";
    public static ArrayList<User> users = new ArrayList<>();
    public static Map<String, String> token_name = new HashMap<String, String>();
    public static Map<String, Date> tokenExpiration = new HashMap<String, Date>();


    @POST
    @Path("/register")
    public String createUser(@FormParam("id") String id, @FormParam("username") String username, @FormParam("password") String password) {
        User user = new User(id,username, password);
        users.add(user);
        return "User: " + username + "Created !!";
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    public javax.ws.rs.core.Response login(@FormParam("username") String username, @FormParam("password") String password) {
        User user = users.stream().filter(user1 -> user1.getName().equals(username)).findFirst().orElse(null);
        Response_two authentication;

        javax.ws.rs.core.Response.Status status;
        if (user == null) {
            authentication = new Response_two(false, "");
            status = javax.ws.rs.core.Response.Status.FORBIDDEN;
        }
        else{
            if (user.getPassword().equals(password))
            {
                user.generateToken();
                token_name.put(user.getToken(), username);
                tokenExpiration.put(user.getToken(), new Date());
                authentication = new Response_two(true, user.getToken());
                status = javax.ws.rs.core.Response.Status.OK;
            }
            else{
                authentication = new Response_two(false, "");
                status = javax.ws.rs.core.Response.Status.UNAUTHORIZED;
            }
        }
        return javax.ws.rs.core.Response.status(status).entity(authentication).build();
    }


    @POST
    @Path("/logout")
    @Produces("application/json")
    public String logout(@FormParam("username") String username) {
        User user = users.stream().filter(user1 -> user1.getName().equals(username)).findFirst().orElse(null);

        if (user == null) {
            return "User does not exist!";
        }
        else{
            if(user.getToken().equals("")){
                return "Not logged in right now!";
            }
            else{
                token_name.remove(user.getToken());
                tokenExpiration.remove(user.getToken());
                user.destroyToken();
                return "Logged out. Token destroyed!";
            }
        }

    }
}