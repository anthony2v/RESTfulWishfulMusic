package com.spicecrispies.service;

import com.spicecrispies.core.entities.MyResponse;
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

    private static ArrayList<User> users = new ArrayList<>();
    private static UserMapper userMapper = new UserMapper();
    private static Map<String, String> tokenUsername = new HashMap<String, String>();
    private static Map<String, Date> tokenExpiration = new HashMap<String, Date>();
    public static String tokenHeader="";



    @POST
    @Path("/register")
    @Produces("application/json")
    public String register(@FormParam("id") String id,@FormParam("username") String username, @FormParam("password") String password) throws SQLException, ClassNotFoundException {
        User user = new User(id,username, password);
        users.add(user);
        userMapper.insert(user);
        return "User Created - : " + username;
    }


    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        User user = users.stream().filter(user1 -> user1.getName().equals(username)).findFirst().orElse(null);
        MyResponse authResponse;
        Response.Status status;
        if(user != null){
            if(user.getPassword().equals(password)){
                user.generateToken();
                tokenUsername.put(user.getToken(), username);
                tokenExpiration.put(user.getToken(), new Date());
                authResponse = new MyResponse(true, user.getToken());
                status = Response.Status.OK;
            }
            else{
                authResponse = new MyResponse(false, "");
                status = Response.Status.UNAUTHORIZED;
            }
        }
        else{
            authResponse = new MyResponse(false, "");
            status = Response.Status.FORBIDDEN;
        }
        return Response.status(status).entity(authResponse).build();
    }


    @POST
    @Path("/logout")
    @Produces("application/json")
    public String logout(@FormParam("username") String username) {
        User user = users.stream().filter(user1 -> user1.getName().equals(username)).findFirst().orElse(null);

        if (user == null) {
            return "User does not exist!";
        }
        else {
            if(user.getToken().equals("")) {
                return "User Not logged in.";
            }
            else{
                tokenUsername.remove(user.getToken());
                tokenExpiration.remove(user.getToken());
                user.destroyToken();
                return "Logged out. Token succesfully destroyed.";
            }
        }

    }


    @POST
    @Path("/authentication")
    public Response validateToken(@HeaderParam("x-api-key") String token) {
        if(tokenUsername.containsKey(token)){
            Date timeNow = new Date();
            long diff = timeNow.getTime() - tokenExpiration.get(token).getTime();
            long tokenDuration = TimeUnit.MILLISECONDS.toMinutes(diff);
            System.out.println("Duration: " + tokenDuration);
            if(tokenDuration > 30){
                tokenUsername.remove(token);
                tokenExpiration.remove(token);
            }
            else{
                return Response.status(Response.Status.OK).entity("Token Correct").build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Token Invalid").build();
    }
}
