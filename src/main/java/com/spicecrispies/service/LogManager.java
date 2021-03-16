package com.spicecrispies.service;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.persistence.AlbumMapper;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogManager implements LogManagerInterface {
    AlbumMapper album_mapper;
    List<Album> logs = new ArrayList<>();
    public List<Album> listLog() {

        logs = AlbumMapper.selectAll();

        return logs;  //Get the whole list
    }

    //Get list based on ChangeType and isrc
    public List<Album> listLog(ChangeType typeOfChange, String isrc) {
       
        logs = (List<Album>) AlbumMapper.select(isrc);

        return logs;
    }

    @Override
    public boolean addLog(LogEntry log) throws RepException {
        LocalDateTime date = log.getDateTime();
        String typeOfChange = log.getChangeType().toString();
        String recordKey = log.getRecordKey();

        if(recordKey.equals("") || typeOfChange.equals(""))
        {
            throw new RepException("RECORD KEY AND CHANGETYPE MISSING");
        }

        album_mapper.
        return added;
    }


    public String clearLogs() throws RepException {
        throw new RepException("ERROR: CLEAR LOG is not yet supported.");
    }
}
