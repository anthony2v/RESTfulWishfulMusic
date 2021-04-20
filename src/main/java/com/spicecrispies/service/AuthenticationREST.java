package com.spicecrispies.service;

import com.spicecrispies.core.entities.Response_two;
import com.spicecrispies.repository.LoginManager;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/user")
public class AuthenticationREST {

    public static AuthenticationREST getInstance() {
        return AuthenticationREST.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final AuthenticationREST INSTANCE = new AuthenticationREST();
    }

    @POST
    @Path("/register/{id}/{username}/{password}")
    @Produces("application/json")
    public String register(@PathParam("id")  String id,@PathParam("username")  String username, @PathParam("password") String password) throws SQLException, ClassNotFoundException {
        LoginManager.getInstance().addUser(id,username,password);
        return LoginManager.getInstance().getUser(username).toString();
    }

    @POST
    @Path("/login/{ID}/{username}/{password}")
    @Produces("application/json")
    public Response login(@PathParam("ID") String ID,@PathParam("username") String username, @PathParam("password") String password) {
        Response_two authenticationResponse;
        Response.Status status;
        String token = LoginManager.getInstance().generateToken(ID,password);
        if(token == null){
            authenticationResponse = new Response_two(false, "");
            status = Response.Status.FORBIDDEN;
        }
        else{
            authenticationResponse = new Response_two(true, token);
            status = Response.Status.OK;
        }
        return Response.status(status).entity(authenticationResponse).build();
    }

    @POST
    @Path("/logout/{ID}")
    @Produces("application/json")
    public String logout(@PathParam("ID") String id) throws SQLException, ClassNotFoundException {
        LoginManager.getInstance().getUser(id);
        if (LoginManager.getInstance().getUser(id)== null){
            return "User does not exist!";
        }
        else{
            if (LoginManager.getInstance().getUser(id).getToken().equals("")) {
                return "You are Not logged in.";
            }
            else
            {
                LoginManager.getInstance().removeToken(id);
                return "Logged out. Token destroyed!!.";
            }
        }
    }
}