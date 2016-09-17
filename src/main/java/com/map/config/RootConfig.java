package com.map.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Andrew Pasika
 */
@Configuration
public class RootConfig {

    @Bean
    @Scope("prototype")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
