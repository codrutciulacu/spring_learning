package com.codrut.spring_database_example.dataAccessLayer;

import java.util.List;
import java.util.Optional;

import com.codrut.spring_database_example.domain.Album;

public interface AlbumDao {
    List<Album> findAll();
    List<Album> findBySinger();
    List<Album> findByTitle();
    Optional<Album> findById(Long id);
    void insert(Album album);
    void update(Album album);
    void delete(Long albumId);
}
