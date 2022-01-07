package com.codrut.hooking_into_bean_creation.interfaces;

import com.codrut.hooking_into_bean_creation.annotations.SingerWithAnnotation;
import com.codrut.hooking_into_bean_creation.methods.SingerWithMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@Data
@AllArgsConstructor
public class SingerWithInterface implements InitializingBean {

    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private Integer age = Integer.MIN_VALUE;

    public SingerWithInterface() {
    }

    public String toString()  {
        return "\tName: " + name + "\n\tAge: " + age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " + SingerWithInterface.class);
        }
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/interfaces-app-context-xml.xml");
        ctx.refresh();

        getBean("singer1", ctx);
        getBean("singer2", ctx);
        getBean("singer3", ctx);

        ctx.close();
    }

    private static SingerWithInterface getBean(String beanName, @NonNull ApplicationContext ctx) {
        try {
            SingerWithInterface bean = (SingerWithInterface) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        }catch (BeanCreationException e){
            System.out.println("An error occured in bean configuration: "
                    +  e.getMessage());
            return null;
        }
    }
}
