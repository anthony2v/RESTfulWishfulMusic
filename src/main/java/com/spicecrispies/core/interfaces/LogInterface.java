package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.logging.LogEntry;

import java.util.ArrayList;

public interface LogInterface {
    public ArrayList<LogEntry> getChangeLogs(String fromDate, String toDate, String queryType);
    public boolean clearLogs();
}
