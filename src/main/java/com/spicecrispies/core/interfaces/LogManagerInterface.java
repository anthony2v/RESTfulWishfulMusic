package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding
public interface LogManagerInterface {
    @WebMethod
    boolean addLog(String dateTime, ChangeType changeType, String recordKey) throws RepException;
    @WebMethod
    List<LogEntry> getChangeLogs(String from, String to, String changeType) throws RepException;
    @WebMethod
    String clearLogs() throws RepException;

}
