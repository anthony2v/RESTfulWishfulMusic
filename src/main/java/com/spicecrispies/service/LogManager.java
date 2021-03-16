package com.spicecrispies.service;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.core.logging.LogFault;

import java.sql.Timestamp;
import java.util.ArrayList;

public class LogManager implements LogManagerInterface {
    @Override
    public ArrayList<LogEntry> listLog() {
        return null;
    }

    @Override
    public ArrayList<LogEntry> listLog(Timestamp fromDate, Timestamp toDate) {
        return null;
    }

    @Override
    public ArrayList<LogEntry> listLog(Timestamp fromDate, Timestamp toDate, ChangeType typeOfChange) {
        return null;
    }

    @Override
    public ArrayList<LogEntry> listLog(ChangeType typeOfChange) {
        return null;
    }

    @Override
    public boolean addLog(LogEntry log) throws RepException {
        return false;
    }

    @Override
    public String clearLogs() throws RepException {
        return null;
    }
}
