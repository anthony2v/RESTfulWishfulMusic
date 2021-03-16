package com.spicecrispies.core.logging;

import com.spicecrispies.core.enums.ChangeType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LogEntry {

    private Timestamp timestamp;  // not sure about this
    private ChangeType changeType;
    private String recordKey;

    public LogEntry() { }

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


    public String toString(){
        String logInfo;
        logInfo = "Date TimeStamp: " + timestamp + " , changeType: " + changeType + " , Record Key: " + recordKey + "\n";
        return logInfo;
    }
}


