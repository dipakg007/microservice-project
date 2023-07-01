package com.project.employee.service;

import com.project.domain.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentServiceProxy {
    @GetMapping("/api/departments/departmentCode/{departmentCode}")
    DepartmentDto getDepartmentByCode(@PathVariable String departmentCode);
}
