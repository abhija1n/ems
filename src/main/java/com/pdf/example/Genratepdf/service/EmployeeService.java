package com.pdf.example.Genratepdf.service;

import com.pdf.example.Genratepdf.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getByID(Integer id);

    Employee updateEmployee(Employee employee, Integer id);

    Employee partialUpdateEmployee(Integer id, Employee employee);

    void deleteEmployee(Integer id);
}
