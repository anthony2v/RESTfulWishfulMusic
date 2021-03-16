package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface LogManagerInterface {
    ArrayList<LogEntry> listLog();
    ArrayList<LogEntry> listLog(Timestamp fromDate, Timestamp toDate);
    ArrayList<LogEntry> listLog(Timestamp fromDate, Timestamp toDate, ChangeType typeOfChange);
    ArrayList<LogEntry> listLog(ChangeType typeOfChange);
    boolean addLog(LogEntry log) throws RepException;
    String clearLogs() throws RepException;

}
