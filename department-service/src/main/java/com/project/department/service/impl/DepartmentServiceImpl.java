package com.project.department.service.impl;

import com.project.department.repository.DepartmentRepository;
import com.project.department.service.DepartmentService;
import com.project.domain.dto.DepartmentDto;
import com.project.domain.entity.Department;
import com.project.domain.exception.ResourceNotFoundException;
import com.project.domain.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = optionalDepartment.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return DepartmentMapper.toDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = optionalDepartment.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());
        Department updatedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentCode);
        Department department = optionalDepartment.orElseThrow(() -> new ResourceNotFoundException("Department not found with Department Code: " + departmentCode));
        return DepartmentMapper.toDto(department);
    }
}


