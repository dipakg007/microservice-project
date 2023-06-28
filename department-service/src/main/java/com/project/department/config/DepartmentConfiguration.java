package com.project.department.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.project.domain.entity"})
public class DepartmentConfiguration {
}
