package com.spicecrispies.service;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.LogInterface;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.core.logging.LogFault;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SOAPLogEntry implements LogInterface {
    private LogManagerInterface logManaging = (LogManagerInterface) new LogManager();


    @Override
    public void getChangeLogs(Timestamp fromDate, Timestamp toDate, String changeType) throws LogFault{
        ArrayList<LogEntry> logs = new ArrayList<>();
        Timestamp fromDateTime = null;
        Timestamp toDateTime = null;
        ChangeType change_type = null;

        if(!changeType.equals(""))
        {
            if(changeType.equals("CREATE") || changeType.equals("UPDATE") || changeType.equals("DELETE"))
            {
                change_type = ChangeType.valueOf(changeType);
            }
            else{
                throw new LogFault("ONLY valid ChangeType: CREATE , UPDATE, DELETE");
            }
        } }


    @Override
        public String clearLogs() throws LogFault{
        try {
            logManaging.clearLogs();

            return "Log cleared";
        } catch(RepException e) {
            throw new LogFault(e.getMessage());
        }
    }
}
