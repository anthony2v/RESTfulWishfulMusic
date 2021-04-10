package com.spicecrispies.repository;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.interfaces.AlbumRepoInterface;
import com.spicecrispies.persistence.AlbumMapper;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class AlbumRepoImplementation implements AlbumRepoInterface, Serializable {
    private static final int MAX_AVAILABLE = 1;
    private static final Semaphore sema = new Semaphore(MAX_AVAILABLE, true);

    @Override
    public boolean createAlbum(Album album) throws ClassNotFoundException, InterruptedException, SQLException {
        if (getAlbumInfo(album.getIsrc()) != null)
            return false;
        getLock();
        AlbumMapper.insert(new Album(album));
        releaseLock();
        return true;
    }

    @Override
    public boolean deleteAlbum(String isrc) throws ClassNotFoundException, InterruptedException, SQLException {
        if (getAlbumInfo(isrc) == null)
            return false;
        getLock();
        AlbumMapper.delete(isrc);
        releaseLock();
        return true;
    }

    @Override
    public Album getAlbumInfo(String isrc) throws ClassNotFoundException, SQLException {
        return AlbumMapper.select(isrc);
    }

    @Override
    public ArrayList<Album> listAlbums() throws ClassNotFoundException, SQLException {
        return AlbumMapper.selectAll();
    }

    private void getLock() throws InterruptedException {
        sema.acquire();
    }

    private void releaseLock(){
        sema.release();
    }
}