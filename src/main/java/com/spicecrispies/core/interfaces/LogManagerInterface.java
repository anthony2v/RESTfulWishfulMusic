package com.spicecrispies.core.interfaces;

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
    List<LogEntry> getChangeLogs();
    List<LogEntry> getChangeLogs(String fromDate, String toDate, String changeType) throws RepException;
    boolean addLog(LogEntry log) throws RepException;
    String clearLogs() throws RepException;

}
