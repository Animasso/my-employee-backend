package com.javab.blog_backend.controller;

import com.javab.blog_backend.dto.EmployeeDto;
import com.javab.blog_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
   private EmployeeService employeeService;


   //Add an employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Get an Employee
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
      EmployeeDto employeeDto =  employeeService.getEmployeeById(employeeId);
        return  ResponseEntity.ok(employeeDto);
    }

    //get all Employees
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getAllEmployees(){
       List<EmployeeDto> employees= employeeService.getAllEmployees();
        System.out.println("Fetching employees...");
       return ResponseEntity.ok(employees);
    }

//update employee
    @PutMapping("{id}")
        public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto= employeeService.updateEmployee(employeeId,updateEmployee);
        return  ResponseEntity.ok(employeeDto);
    }

//delete employee
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
    employeeService.deleteEmployee(employeeId);
    return ResponseEntity.ok("Employee deleted successfully");
    }
}
