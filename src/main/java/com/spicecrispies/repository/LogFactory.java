package com.spicecrispies.repository;

import com.spicecrispies.core.logging.LogInterface;

public class LogFactory {
    private static LogInterface logInterface;

    private LogFactory(){}

    public static synchronized LogInterface getInstance(){
        if(logInterface == null){
            logInterface= new LogImplementation();
        }
        return logInterface;
    }
}
