package com.spicecrispies.core.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Blob;

public class AlbumCover {

    private byte[] albumCoverImage;
    private String mimeType;
    private String isrc;

    public AlbumCover(byte[] albumCoverImage, String mimeType) {
        this.albumCoverImage = albumCoverImage;
        this.mimeType = mimeType;
    }
    public AlbumCover(String isrc){
        this.isrc = isrc;
        albumCoverImage= new byte[1];
        mimeType = "";

    }

    public byte[] getAlbumCoverImage() {
        return albumCoverImage;
    }

    public void setAlbumCoverImage(byte[] albumCoverImage) {
        this.albumCoverImage = albumCoverImage;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }
}

