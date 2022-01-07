package com.codrut.spring_database_example.dataAccessLayer;

import com.codrut.spring_database_example.domain.Singer;

import java.util.List;
import java.util.Optional;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    List<Singer> findAllWithAlbums();
    Optional<String> findLastNameById(Long id);
    Optional<String> findFirstNameById(Long id);
    void insert(Singer singer);
    void update(Singer singer);
    void delete(Long singerId);
}
