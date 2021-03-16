package com.spicecrispies.repository;


import com.spicecrispies.core.interfaces.LogManagerInterface;

public class LogManagerFactory {

    private static LogManagerInterface logManagerInterface;

    private LogManagerFactory(){}

    public static synchronized LogManagerInterface getInstance(){
        if(logManagerInterface == null){
            logManagerInterface = new LogManagerImplementation();
        }
        return logManagerInterface;
    }
}
