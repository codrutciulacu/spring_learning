package com.codrut.dependency_lookup.dp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyPull {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/app-context");

        MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }

}
