package com.spicecrispies.core.logging;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;


@WebService
@SOAPBinding
public interface LogInterface {
    @WebMethod(operationName = "listLog")
    public ArrayList<LogEntry> listLog(@WebParam(name="from") String from, @WebParam(name="to") String to, @WebParam(name="changeType") String changeType) throws LogFault;
    @WebMethod(operationName = "clearLog")
    public String clearLog() throws LogFault;
}
