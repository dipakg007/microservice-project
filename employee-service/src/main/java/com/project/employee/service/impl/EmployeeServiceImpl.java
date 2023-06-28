package com.project.employee.service.impl;

import com.project.domain.dto.EmployeeDto;
import com.project.domain.entity.Employee;
import com.project.domain.exception.DuplicateEmailException;
import com.project.domain.exception.ResourceNotFoundException;
import com.project.domain.mapper.EmployeeMapper;
import com.project.employee.repository.EmployeeRepository;
import com.project.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Check if the email already exists
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + employeeDto.getEmail());
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
    public EmployeeDto getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = employeeOptional.orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + id));
        return EmployeeMapper.toDto(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}

