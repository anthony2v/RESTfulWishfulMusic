package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface LogManagerInterface {
    List<LogEntry> getChangeLogs();
    List<LogEntry> getChangeLogs(String fromDate, String toDate, String changeType) throws com.spicecrispies.core.logging.RepException;
    boolean addLog(LogEntry log) throws RepException, com.spicecrispies.core.logging.RepException;
    String clearLogs() throws RepException, com.spicecrispies.core.logging.RepException;

}
