package com.codrut.hooking_into_bean_creation.config;

import com.codrut.hooking_into_bean_creation.methods.SingerWithMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Singer {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private Integer age = Integer.MIN_VALUE;

    public Singer() {
    }

    public void init() {
        System.out.println("Initializing bean");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " + SingerWithMethod.class);
        }
    }

    public String toString()  {
        return "\tName: " + name + "\n\tAge: " + age;
    }

}
