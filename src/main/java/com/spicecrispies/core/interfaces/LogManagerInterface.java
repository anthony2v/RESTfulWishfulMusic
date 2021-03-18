package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding
public interface LogManagerInterface {

    @WebMethod(operationName = "addLogs")
    boolean addLog(LogEntry log) throws RepException;
    @WebMethod(operationName = "getChangeLogs")
    public List<LogEntry> getChangeLogs(@WebParam(name="from") String from, @WebParam(name="to") String to, @WebParam(name="changeType") String changeType) throws RepException;
    @WebMethod(operationName = "clearLog")
    public String clearLogs() throws RepException;

}
