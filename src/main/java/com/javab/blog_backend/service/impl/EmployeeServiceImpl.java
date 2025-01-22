package com.javab.blog_backend.service.impl;

import com.javab.blog_backend.dto.EmployeeDto;
import com.javab.blog_backend.entity.Employee;
import com.javab.blog_backend.exception.RessourceNotFoundExeption;
import com.javab.blog_backend.mapper.EmployeeMapper;
import com.javab.blog_backend.repository.EmployeeRepository;
import com.javab.blog_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RessourceNotFoundExeption("Employee with id " + employeeId + " not found"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
      List<Employee> employees= employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
    Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()->new RessourceNotFoundExeption("Employee doesn't exists with the given id :"+ employeeId)
        );
    employee.setFirstName(updateEmployee.getFirstName());
    employee.setLastName(updateEmployee.getLastName());
    employee.setEmail(updateEmployee.getEmail());
    Employee updateEmployeeObj= employeeRepository.save(employee);
    return  EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()->new RessourceNotFoundExeption("Employee doesn't exists with the given id :"+ employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }
}
