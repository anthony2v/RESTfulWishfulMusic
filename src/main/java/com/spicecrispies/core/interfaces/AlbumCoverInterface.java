package com.spicecrispies.core.interfaces;

import java.io.InputStream;
import java.sql.SQLException;
import com.spicecrispies.core.entities.AlbumCover;


public interface CoverImageInterface {

    AlbumCover createAlbumCover(InputStream imageBlob, String mimeType, String isrc) throws SQLException;
    AlbumCover getAlbumCoverISRC(String isrc) throws SQLException;
    boolean deleteAlbumCover (String isrc) throws SQLException;

}
