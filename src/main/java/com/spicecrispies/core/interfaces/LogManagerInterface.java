package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.enums.QueryType;
import com.spicecrispies.core.logging.LogEntry;

import java.util.ArrayList;

public interface LogManagerInterface {
    boolean addLog(String dateTime, QueryType queryType, String recordKey);
    ArrayList<String> getChangeLogs(String changeType);
    boolean clearLogs();
}
