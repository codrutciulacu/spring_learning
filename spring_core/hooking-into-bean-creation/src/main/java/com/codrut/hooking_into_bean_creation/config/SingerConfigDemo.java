package com.codrut.hooking_into_bean_creation.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

public class SingerConfigDemo {

    @Configuration
    static class SingerConfig {

        @Lazy
        @Bean(initMethod = "init")
        Singer singerOne() {
            Singer singer = new Singer();
            singer.setName("John Mayers");
            singer.setAge(39);
            return singer;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singerTwo() {
            Singer singer = new Singer();
            singer.setAge(29);
            return singer;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singerThree() {
            Singer singer = new Singer();
            singer.setName("John Butler");
            return singer;
        }

    }


    public static void main(String[] args) {
        GenericApplicationContext ctx  =
                new AnnotationConfigApplicationContext(SingerConfig.class);
        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);
        ctx.close();
    }

    private static Singer getBean(String beanName, GenericApplicationContext ctx) {
        try {
            Singer singer = (Singer) ctx.getBean(beanName);
            System.out.println(singer);
            return singer;
        }catch (BeanCreationException e) {
            System.out.println("An error occured in bean configuration: " + e.getMessage());
            return null;
        }
    }
}
