package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.logging.LogFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Timestamp;


@WebService
@SOAPBinding
public interface LogInterface {
    @WebMethod(operationName = "ChangeLog")
    public void getChangeLogs(@WebParam(name="fromDate") Timestamp fromDate, @WebParam(name="ToDate")Timestamp toDate, @WebParam(name="changeType") String changeType) throws LogFault;
    @WebMethod(operationName = "clearLog")
    public String clearLogs() throws LogFault;
}
