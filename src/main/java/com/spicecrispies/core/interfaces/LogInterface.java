package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.core.logging.LogFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding
public interface LogInterface {
    @WebMethod(operationName = "listLog")
    public List<LogEntry> getChangeLogs(@WebParam(name="from") String fromDate, @WebParam(name="to") String toDate, @WebParam(name="changeType") String changeType) throws LogFault;
    @WebMethod(operationName = "clearLog")
    public String clearLogs() throws LogFault;
}
