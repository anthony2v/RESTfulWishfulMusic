package com.spicecrispies.repository;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.core.logging.RepException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogManagerImplementation implements LogManagerInterface {
    private static final List<LogEntry> logs = Collections.synchronizedList(new ArrayList<>());

    public boolean addLog(LogEntry log) throws RepException {
        if (log == null || log.getDateTime() == null || log.getChangeType() == null || log.getRecordKey() == null) {
            throw new RepException("ERROR: Missing log attributes");
        }
        synchronized (logs) {
            logs.add(log);
        }
        return true;
    }

    @Override
    public List<LogEntry> getChangeLogs() {
        return logs;
    }

    @Override
    public List<LogEntry> getChangeLogs(String fromDate, String toDate, String changeType) throws RepException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;
        ChangeType type = null;
        if (!changeType.equals("")) {
            if (changeType.equals("CREATE") || changeType.equals("UPDATE") || changeType.equals("DELETE")) {
                type = ChangeType.valueOf(changeType);
            }
            else {
                throw new RepException("Only valid ChangeType(CREATE, UPDATE, DELETE) accepted !!");
            }
        }
        if (fromDate != null && !fromDate.equals("")) {
            try {
                fromDateTime = LocalDateTime.parse(fromDate, dateFormatter);
            }
            catch (DateTimeParseException pe) {
                throw new RepException("ERROR: Date format should be yyyy-MM-dd HH:mm:ss");
            }
        }
        if (toDate != null && !toDate.equals("")) {
            try {
                toDateTime = LocalDateTime.parse(toDate, dateFormatter);
            }
            catch (DateTimeParseException pe) {
                throw new RepException("ERROR: Date format should be yyyy-MM-dd HH:mm:ss");
            }
        }
        if (fromDateTime == null || toDateTime == null) {
            throw new RepException("ERROR: Cannot accept null dates");
        }
        List<LogEntry> toReturn = new ArrayList<>();
        for (LogEntry entry: logs) {
            if (entry.getDateTime().isAfter(fromDateTime) && entry.getDateTime().isBefore(toDateTime)) {
                toReturn.add(entry);
            }
        }
        return toReturn;
    }

    @Override
    public String clearLogs() throws RepException {
        throw new RepException("Method not implemented.");
    }
}
