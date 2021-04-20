package com.spicecrispies.core.entities;

import java.io.Serializable;

public class Response_two implements Serializable {

    private boolean success;
    private String token;
    private static final long serialVersionUID = 115L;

    public Response_two(){
        super();
    }

    public Response_two(boolean success, String token){
        super();
        this.success = success;
        this.token = token;
    }

    public boolean getSuccess(){ return this.success; }

    public void setSuccess(boolean success){ this.success = success; }

    public String getToken(){ return this.token; }

    public void setToken(String token){ this.token = token; }

    public String toString(){
        return "Success: " + this.success + " and  Token: " + this.token;
    }
}