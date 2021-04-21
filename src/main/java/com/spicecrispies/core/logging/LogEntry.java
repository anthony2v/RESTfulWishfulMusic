package com.spicecrispies.core.logging;

import com.spicecrispies.core.enums.QueryType;

public class LogEntry {
    private String dateTime;
    private QueryType queryType;
    private String recordKey;

    public LogEntry(String dateTime, QueryType queryType, String recordKey) {
        this.dateTime = dateTime;
        this.queryType = queryType;
        this.recordKey = recordKey;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public QueryType getChangeType() {
        return queryType;
    }

    public void setChangeType(QueryType queryType) {
        this.queryType = queryType;
    }

    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    public String toString(){
        return String.format("Date & Time: %s, Change Type: %s, Record Key: %s", dateTime, queryType, recordKey);
    }
}
