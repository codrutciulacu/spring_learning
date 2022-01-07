package com.codrut.dependency_injection.injection_parameters.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ComponentScan
@Configuration("injectSimpleConfig")
public class InjectSimpleConfig {
    private String name = "John Mayer";
    private int age = 39;
    private float height = 1.92f;
    private boolean programmer = false;
    private Long ageInSeconds = 1_241_401_112L;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public float getHeight() {
        return height;
    }
    public boolean isProgrammer() {
        return programmer;
    }
    public Long getAgeInSeconds() {
        return ageInSeconds;
    }
}
