package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface LogManagerInterface {
    List<Album> listLog();
    List<Album> listLog(ChangeType typeOfChange, String isrc);

    boolean addLog(LogEntry log) throws RepException;
    String clearLogs() throws RepException;

}
