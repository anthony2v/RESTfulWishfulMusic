package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.enums.QueryType;

public interface LogManagerInterface {
    boolean addLog(String dateTime, QueryType queryType, String recordKey);
    String getChangeLogs(String from, String to, String changeType);
    String clearLogs();
}
