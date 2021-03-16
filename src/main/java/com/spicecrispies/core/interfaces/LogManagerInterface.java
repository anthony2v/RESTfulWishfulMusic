package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;

import java.util.List;

public interface LogManagerInterface {
    List<LogEntry> getChangeLogs();
    List<LogEntry> getChangeLogs(String fromDate, String toDate, String changeType) throws RepException;
    boolean addLog(LogEntry log) throws RepException;
    String clearLogs() throws RepException;

}
