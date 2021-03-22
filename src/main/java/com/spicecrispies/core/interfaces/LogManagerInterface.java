package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface LogManagerInterface {

    @WebMethod
    boolean addLog(String dateTime, ChangeType changeType, String recordKey) throws RepException;
    @WebMethod
    String getChangeLogs(String from, String to, String changeType) throws RepException;
    @WebMethod
    String clearLogs() throws RepException;

}
