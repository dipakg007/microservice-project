package com.project.domain.mapper;

import com.project.domain.dto.EmployeeDto;
import com.project.domain.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDepartmentCode(employee.getDepartmentCode());
        return employeeDto;
    }

    public static Employee toEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        return employee;
    }
}
