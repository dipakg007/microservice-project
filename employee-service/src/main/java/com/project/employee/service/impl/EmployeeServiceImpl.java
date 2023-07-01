package com.project.employee.service.impl;

import com.project.domain.dto.APIResponseDto;
import com.project.domain.dto.DepartmentDto;
import com.project.domain.dto.EmployeeDto;
import com.project.domain.entity.Employee;
import com.project.domain.exception.EmailAlreadyExistsException;
import com.project.domain.exception.ResourceNotFoundException;
import com.project.domain.mapper.EmployeeMapper;
import com.project.employee.repository.EmployeeRepository;
import com.project.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WebClient webClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Check if the email already exists
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + employeeDto.getEmail());
        }

        Employee employee = EmployeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = employeeOptional.orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + id));
        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/departmentCode/" + employee.getDepartmentCode()).retrieve().bodyToMono(DepartmentDto.class).block();
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(EmployeeMapper.toDto(employee));

        return apiResponseDto;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = employeeOptional.orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + id));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}

