package com.project.employee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.project.domain.entity"})
@ComponentScan(basePackages = {"com.project.domain"})
public class EmployeeConfiguration {
}
