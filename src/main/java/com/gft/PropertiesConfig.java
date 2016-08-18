package com.gft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:${starter.property.file}")
class PropertiesConfig {

    @Autowired
    private Environment environment;

    public String getProperty(String name) {
        return environment.getProperty(name);
    }

}
