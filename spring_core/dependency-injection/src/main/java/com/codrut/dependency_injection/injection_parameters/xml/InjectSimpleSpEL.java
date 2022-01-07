package com.codrut.dependency_injection.injection_parameters.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectSimpleSpEL {
    private String name;
    private int age;
    private float height;
    private boolean programmer;
    private Long ageInSeconds;

    public static void main(String[] args) {
        GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext();
        applicationContext.load("spring/app-context-spel-xml.xml");
        applicationContext.refresh();

        InjectSimpleSpEL simpleSpEL = applicationContext.getBean("injectSimpleSpel", InjectSimpleSpEL.class);
        System.out.println(simpleSpEL);

        applicationContext.close();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setProgrammer(boolean programmer) {
        this.programmer = programmer;
    }

    public void setAgeInSeconds(Long ageInSeconds) {
        this.ageInSeconds = ageInSeconds;
    }

    @Override
    public String toString() {
        return "InjectSimpleSpEL{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", programmer=" + programmer +
                ", ageInSeconds=" + ageInSeconds +
                '}';
    }
}
