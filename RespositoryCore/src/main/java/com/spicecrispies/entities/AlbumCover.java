package com.spicecrispies.entities;

public class AlbumCover {

    private byte[] albumCoverImage;
    private String mimeType;

    public AlbumCover(byte[] albumCoverImage, String mimeType) {
        this.albumCoverImage = albumCoverImage;
        this.mimeType = mimeType;
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
}
