package com.spicecrispies.repository;

import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.core.exceptions.RepException;

import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebService(endpointInterface = "com.spicecrispies.core.interfaces.LogManagerInterface")
public class LogManagerImplementation implements LogManagerInterface {
    private static final List<LogEntry> logs = Collections.synchronizedList(new ArrayList<>());

    @Override
    public boolean addLog(String dateTime, ChangeType changeType, String recordKey) throws RepException {
        if (dateTime == null || changeType == null || recordKey == null) {
            throw new RepException("ERROR: Missing log attributes");
        }
        synchronized (logs) {
            logs.add(new LogEntry(dateTime, changeType, recordKey));
        }
        return true;
    }

    @Override
    public String getChangeLogs(String fromDate, String toDate, String changeType) throws RepException {
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
    public String clearLogs() throws RepException {
        throw new RepException("Method not implemented.");
    }
}
