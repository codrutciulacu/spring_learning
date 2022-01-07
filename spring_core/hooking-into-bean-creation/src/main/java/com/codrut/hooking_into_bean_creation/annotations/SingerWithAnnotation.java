package com.codrut.hooking_into_bean_creation.annotations;

import com.codrut.hooking_into_bean_creation.methods.SingerWithMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;


@Data
@AllArgsConstructor
public class SingerWithAnnotation {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private Integer age = Integer.MIN_VALUE;

    public SingerWithAnnotation() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing bean");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " + SingerWithAnnotation.class);
        }
    }

    public String toString()  {
        return "\tName: " + name + "\n\tAge: " + age;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/annotation-app-context-xml.xml");
        ctx.refresh();

        getBean("singer1", ctx);
        getBean("singer2", ctx);
        getBean("singer3", ctx);

        ctx.close();
    }

    private static SingerWithAnnotation getBean(String beanName, @NonNull ApplicationContext ctx) {
        try {
            SingerWithAnnotation bean = (SingerWithAnnotation) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        }catch (BeanCreationException e){
            System.out.println("An error occured in bean configuration: "
                    +  e.getMessage());
            return null;
        }
    }
}
