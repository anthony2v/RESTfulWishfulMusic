package com.spicecrispies.core.logging;

import com.spicecrispies.core.enums.ChangeType;

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
    private ChangeType changeType;
    @XmlElement
    private String recordKey;

    public LogEntry(String dateTime, ChangeType changeType, String recordKey) {
        this.dateTime = dateTime;
        this.changeType = changeType;
        this.recordKey = recordKey;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
        return String.format("Date & Time: %s, Change Type: %s, Record Key: %s", dateTime, changeType, recordKey);
    }
}
