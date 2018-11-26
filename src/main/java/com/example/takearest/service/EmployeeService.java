package com.example.takearest.service;

import com.example.takearest.entity.Employee;

import java.util.List;


/**
 * @author JavaSolutionsGuide
 *
 */
public interface EmployeeService {
    public List<Employee> retrieveEmployees();

    public Employee getEmployee(Long employeeId);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(Long employeeId);

    public void updateEmployee(Employee employee);
}