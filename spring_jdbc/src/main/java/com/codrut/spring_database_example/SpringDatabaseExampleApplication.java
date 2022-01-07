package com.codrut.spring_database_example;

import com.codrut.spring_database_example.config.DbConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringDatabaseExampleApplication {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);
    }
}
