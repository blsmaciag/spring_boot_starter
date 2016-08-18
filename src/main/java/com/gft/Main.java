package com.gft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("################:  "+System.getProperties().getProperty("starter.property.file"));
        System.getProperties().setProperty("starter.property.file", "starter_dev.properties");
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        PropertiesConfig properties = ctx.getBean(PropertiesConfig.class);

        System.out.println(properties.getProperty("test.property"));
        System.out.println("dupsko");
    }

}
