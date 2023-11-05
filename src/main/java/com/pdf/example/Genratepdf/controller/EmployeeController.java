package com.pdf.example.Genratepdf.controller;

import com.pdf.example.Genratepdf.entity.Employee;
import com.pdf.example.Genratepdf.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }
    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);

    }
    @GetMapping("/get")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
        return new ResponseEntity<Employee>(employeeService.getByID(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id,@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee Deleted Successfully",HttpStatus.OK);
    }
    @PatchMapping("/updated/{id}")
    public ResponseEntity<Employee> partialUpdate(@PathVariable Integer id,@RequestBody Employee employee){
        return  new ResponseEntity<Employee>(employeeService.partialUpdateEmployee(id,employee),HttpStatus.OK);
    }

}
