package com.spicecrispies.service;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.interfaces.LogInterface;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.core.logging.RepException;
import com.spicecrispies.repository.LogManagerImplementation;

import javax.jws.WebService;


import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@WebService(endpointInterface = "com.spicecrispies.core.interfaces.LogInterface")
public class SOAPLogEntry implements LogInterface {
    private LogManagerInterface logManaging = (LogManagerInterface) new LogManagerImplementation();

    @Override
    public ArrayList<LogEntry> getChangeLogs(String from, String to, String changeType) throws RepException {
        ArrayList<LogEntry> logs = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;
        ChangeType type = null;

        if(!changeType.equals(""))
        {
            if(changeType.equals("CREATE") || changeType.equals("UPDATE") || changeType.equals("DELETE"))
            {
                type = ChangeType.valueOf(changeType);
            }
            else{
                throw new RepException("Only valid ChangeType(CREATE, UPDATE, DELLETE) accepted !!");
            }
        }

        if(from != null && !from.equals(""))
        {
            try {
                fromDateTime = LocalDateTime.parse(from, dateFormatter);
            }
            catch (DateTimeParseException pe)
            {
                throw new RepException("ERROR: Date format should be yyyy-MM-dd HH:mm:ss");
            }
        }
        if(to != null && !to.equals("")){
            try {
                toDateTime = LocalDateTime.parse(to, dateFormatter);
            }
            catch (DateTimeParseException pe)
            {
                throw new RepException("ERROR: Date format should be yyyy-MM-dd HH:mm:ss");
            }
        }
        return logs;

    }

    @Override
    public String clearLogs() throws RepException {
        try {
            logManaging.clearLogs();
            return "Logs cleared";
        } catch(com.spicecrispies.core.exceptions.RepException e) {
            throw new RepException(e.getMessage());
        }
    }
}

