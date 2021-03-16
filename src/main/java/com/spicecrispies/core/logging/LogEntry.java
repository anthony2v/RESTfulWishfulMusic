package com.spicecrispies.core.logging;

import com.spicecrispies.core.enums.ChangeType;

import java.sql.Timestamp;

public class LogEntry {
    Timestamp timestamp;
    ChangeType changeType;
    String recordKey;

    public LogEntry(Timestamp timestamp, ChangeType changeType, String recordKey) {
        this.timestamp = timestamp;
        this.changeType = changeType;
        this.recordKey = recordKey;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }
}
