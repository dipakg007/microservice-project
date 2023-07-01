package com.project.employee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EntityScan(basePackages = {"com.project.domain.entity"})
@ComponentScan(basePackages = {"com.project.domain"})
public class EmployeeConfiguration {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
