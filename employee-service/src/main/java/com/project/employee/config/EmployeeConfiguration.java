package com.project.employee.config;

import com.project.domain.mapper.EmployeeMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.project.domain.entity")
public class EmployeeConfiguration {
}
