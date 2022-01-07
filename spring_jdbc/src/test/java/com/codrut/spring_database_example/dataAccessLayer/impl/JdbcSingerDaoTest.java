package com.codrut.spring_database_example.dataAccessLayer.impl;

import com.codrut.spring_database_example.config.EmbeddedJdbcConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSingerDaoTest {

    private final JdbcSingerDao dao = new JdbcSingerDao();
    private final GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);

    @BeforeEach
    void setUp() {
        JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
        dao.setJdbcTemplate(template);
    }

    @AfterEach
    void tearDown() {
        ctx.close();
    }

    @Test
    void shouldReturnTheLastNameOfTheFirstSinger() {
        var result = dao.findLastNameById(1L);
        assertTrue(result.isPresent());
        assertEquals("Mayer", result.get());
    }

    @Test
    void shouldReturnTheFirstNameOfTheFirstSinger() {
        var result = dao.findFirstNameById(1L);
        assertTrue(result.isPresent());
        assertEquals("John", result.get());
    }

    @Test
    void shouldReturnAllThreeSingers() {
        var result = dao.findAll();

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Mayer", result.get(0).getLastName());
        assertEquals("1977-10-16", result.get(0).getBirthDate().toString());
        assertEquals("Eric", result.get(1).getFirstName());
        assertEquals("Clapton", result.get(1).getLastName());
        assertEquals("1945-03-30", result.get(1).getBirthDate().toString());
        assertEquals("John", result.get(2).getFirstName());
        assertEquals("Butler", result.get(2).getLastName());
        assertEquals("1975-04-01", result.get(2).getBirthDate().toString());
    }

    @Test
    void shouldReturnTheTwoSingersWithTheFirstNameJohn() {
        var result = dao.findByFirstName("John");

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Butler", result.get(0).getLastName());
        assertEquals("1975-04-01", result.get(0).getBirthDate().toString());
        assertEquals("Mayer", result.get(1).getLastName());
        assertEquals("1977-10-16", result.get(1).getBirthDate().toString());
    }



}