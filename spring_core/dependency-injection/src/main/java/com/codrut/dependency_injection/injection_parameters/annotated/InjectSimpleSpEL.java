package com.codrut.dependency_injection.injection_parameters.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InjectSimpleSpEL {
    @Value("#{injectSimpleConfig.name}")
    private String name;
    @Value("#{injectSimpleConfig.age}")
    private int age;
    @Value("injectSimpleConfig.height")
    private float height;
    @Value("injectSimpleConfig.programmer")
    private boolean programmer;
    @Value("injectSimpleConfig.ageInSeconds")
    private Long ageInSeconds;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InjectSimpleConfig.class);

        InjectSimple injectSimple = applicationContext.getBean("injectSimple", InjectSimple.class);
        System.out.println(injectSimple);

        applicationContext.close();
    }

    @Override
    public String toString() {
        return "InjectSimple{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", programmer=" + programmer +
                ", ageInSeconds=" + ageInSeconds +
                '}';
    }
}
