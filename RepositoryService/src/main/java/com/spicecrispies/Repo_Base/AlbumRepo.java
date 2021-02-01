package com.spicecrispies.Repo_Base;



import java.util.List;

public interface AlbumRepo {

    List<Album> readAll();

    Album read(String isrc);

    void add(Album a);

    void update(Album a);

    void delete(String isrc);
}
