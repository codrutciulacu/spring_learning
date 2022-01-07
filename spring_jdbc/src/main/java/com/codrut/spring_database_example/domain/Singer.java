package com.codrut.spring_database_example.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Singer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<Album> albums;

    public boolean addAlbum(Album album){
        if(album == null){
            albums = new ArrayList<>();
            albums.add(album);
            return true;
        }else {
            if(albums.contains(album)){
                return false;
            }
        }
        albums.add(album);
        return true;
    }
}
