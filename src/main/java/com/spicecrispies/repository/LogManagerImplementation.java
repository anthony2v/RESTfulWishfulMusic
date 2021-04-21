package com.spicecrispies.repository;

import com.spicecrispies.core.enums.QueryType;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;

import java.util.ArrayList;
import java.util.Collections;

public class LogManagerImplementation implements LogManagerInterface {
    private static ArrayList<LogEntry> logs = new ArrayList<>();

    @Override
    public boolean addLog(String dateTime, QueryType queryType, String recordKey) throws NullPointerException {
        if (dateTime == null || queryType == null || recordKey == null) {
            throw new NullPointerException("ERROR: Missing log attributes");
        }
        logs.add(new LogEntry(dateTime, queryType, recordKey));
        return true;
    }

    @Override
    public ArrayList<String> getChangeLogs(String queryType) {
        QueryType type = null;
        if (queryType.equals("CREATE") || queryType.equals("SELECT") || queryType.equals("DELETE")) {
            type = QueryType.valueOf(queryType);
        }
        ArrayList<String> toReturn = new ArrayList<>();
        for (LogEntry entry: logs) {
            if (type == null)
                toReturn.add(entry.toString());
            else if (entry.getChangeType() == type) {
                toReturn.add(entry.toString());
            }
        }
        return toReturn;
    }

    @Override
    public boolean clearLogs() {
        logs = new ArrayList<>();
        return true;
    }
}
