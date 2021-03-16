package com.spicecrispies.core.logging;

import com.spicecrispies.core.enums.ChangeType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LogEntry {

    private LocalDateTime dateTime;
    private ChangeType changeType;
    private String recordKey;

    public LogEntry() { }

    public LogEntry(LocalDateTime dateTime, ChangeType changeType, String recordKey) {
        this.dateTime = dateTime;
        this.changeType = changeType;
        this.recordKey = recordKey;
    }


    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

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
        logInfo = "Date & Time: " + dateTime + " , changeType: " + changeType + " , Record Key: " + recordKey + "\n";
        return logInfo;
    }
}


