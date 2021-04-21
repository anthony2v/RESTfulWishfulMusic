package com.spicecrispies.repository;

import com.spicecrispies.core.entities.User;
import com.spicecrispies.persistence.UserMapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LoginManager {
    private Map<String, User> users = new ConcurrentHashMap<>();
    private Map<String, String> tokenID = new ConcurrentHashMap<>();
    private  Map<String, Date> tokenExpiration = new ConcurrentHashMap<>();
    private String tokenHeader;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final LoginManager INSTANCE = new LoginManager();
    }

    public void addUser(String id,String username, String password) throws SQLException, ClassNotFoundException {
        User user = new User(id,username, password);
        UserMapper.insert(user);
        users.put(username, user);
    }

    public String getUser(String id) throws SQLException, ClassNotFoundException {
        return UserMapper.select(id).getName();
    }

    public String generateToken(String ID , String password){
        User user= users.get(ID);
        if (user== null || !user.getPassword().equals(password)){
            return null;
        }
        user.generateToken();
        String token=user.getToken();
        tokenID.put(token, ID);
        tokenExpiration.put(token, new Date());
        setTokenHeader(token);
        return token;
    }

    public void setTokenHeader(String token){
        this.tokenHeader=token;
    }
    public String getTokenHeader(){
        return this.tokenHeader;
    }

    public Date generateDate(String token){
        return this.tokenExpiration.get(token);
    }

    public void removeToken(String ID){
        User user= users.get(ID);
        tokenID.remove(user.getToken());
        tokenExpiration.remove(user.getToken());
    }
    public void removeTokenByToken(String token){
        tokenID.remove(token);
        tokenExpiration.remove(token);
    }


}

