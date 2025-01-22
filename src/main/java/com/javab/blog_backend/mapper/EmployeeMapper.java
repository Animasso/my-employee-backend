package com.javab.blog_backend.mapper;

import com.javab.blog_backend.dto.EmployeeDto;
import com.javab.blog_backend.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
     return new EmployeeDto(
             employee.getId(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getEmail()
     );
    }
    public  static  Employee mapToEmployee(EmployeeDto employeeDto){
        return  new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
