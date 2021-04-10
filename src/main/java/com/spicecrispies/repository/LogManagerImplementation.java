package com.spicecrispies.repository;

import com.spicecrispies.core.enums.QueryType;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogManagerImplementation implements LogManagerInterface {
    private static final List<LogEntry> logs = Collections.synchronizedList(new ArrayList<>());

    @Override
    public boolean addLog(String dateTime, QueryType queryType, String recordKey) throws NullPointerException {
        if (dateTime == null || queryType == null || recordKey == null) {
            throw new NullPointerException("ERROR: Missing log attributes");
        }
        synchronized (logs) {
            logs.add(new LogEntry(dateTime, queryType, recordKey));
        }
        return true;
    }

    @Override
    public String getChangeLogs(String fromDate, String toDate, String queryType) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;
        QueryType type = null;
        if (!queryType.equals("")) {
            if (queryType.equals("CREATE") || queryType.equals("UPDATE") || queryType.equals("DELETE")) {
                type = QueryType.valueOf(queryType);
            }
            else {
                //throw new Exception("Only valid ChangeType(CREATE, UPDATE, DELETE) accepted !!");
            }
        }
        if (fromDate != null && !fromDate.equals("")) {
            try {
                fromDateTime = LocalDateTime.parse(fromDate, dateFormatter);
            }
            catch (DateTimeParseException pe) {
                //throw new Exception("ERROR: Date format should be yyyy-MM-dd HH:mm:ss");
            }
        }
        if (toDate != null && !toDate.equals("")) {
            try {
                toDateTime = LocalDateTime.parse(toDate, dateFormatter);
            }
            catch (DateTimeParseException pe) {
                //throw new Exception("ERROR: Date format should be yyyy-MM-dd HH:mm:ss");
            }
        }
        if (fromDateTime == null || toDateTime == null) {
            throw new NullPointerException("ERROR: Cannot accept null dates");
        }
        List<LogEntry> toReturn = new ArrayList<>();
        LocalDateTime entryDateTime;
        for (LogEntry entry: logs) {
            entryDateTime = LocalDateTime.parse(entry.getDateTime());
            if (entryDateTime.isAfter(fromDateTime) && entryDateTime.isBefore(toDateTime)) {
                toReturn.add(entry);
            }
        }
        return toReturn.toString();
    }

    @Override
    public String clearLogs() {
        return "clearLogs() called";
    }
}
