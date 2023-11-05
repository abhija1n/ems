package com.pdf.example.Genratepdf.service.impl;

import com.pdf.example.Genratepdf.entity.Employee;
import com.pdf.example.Genratepdf.exception.ResourceNotFoundException;
import com.pdf.example.Genratepdf.repository.EmployeeRepository;
import com.pdf.example.Genratepdf.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getByID(Integer id) {
        Optional<Employee> employee= employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee","ID",id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, Integer id) {
        Employee employee1= employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee", "ID", id));
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());
        employeeRepository.save(employee1);
        return employee1;
    }

    @Override
    public Employee partialUpdateEmployee(Integer id,Employee employee) {
        Employee updatedEmployee= employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee", "ID", id));
        if(employee.getFirstName()!=null){
            updatedEmployee.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName()!=null){
            updatedEmployee.setLastName(employee.getLastName());
        }
        if(employee.getEmail()!=null){
            updatedEmployee.setEmail(employee.getEmail());
        }
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","ID", id));
        employeeRepository.deleteById(id);
    }
}
