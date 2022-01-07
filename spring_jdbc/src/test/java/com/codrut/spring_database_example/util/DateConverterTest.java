package com.codrut.spring_database_example.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateConverterTest {
    @Test
    void shouldConvertTodayDateFromLocalDateToDate() {
        assertEquals(LocalDate.now(), DateConverter.convertDateToLocalDate(new Date()));
    }

}