package com.spicecrispies.core.logging;

import com.spicecrispies.core.enums.QueryType;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LogEntry implements Serializable {

    @XmlElement
    private String dateTime;
    @XmlElement
    private QueryType queryType;
    @XmlElement
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
