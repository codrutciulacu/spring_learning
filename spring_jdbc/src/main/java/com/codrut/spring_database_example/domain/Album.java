package com.codrut.spring_database_example.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Album implements Serializable {
    private Long id;
    private Singer singer;
    private String title;
    private LocalDate releaseDate;
}
