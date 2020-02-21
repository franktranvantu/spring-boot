package com.frank.springboot.springmvc.service;

import com.frank.springboot.springmvc.dao.EmployeeDAO;
import com.frank.springboot.springmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employee> getAllEmployee() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeByName(String name) {
        return employeeDAO.getEmployeeByName(name);
    }

    public List<Employee> getEmployeesByTeam(String team) {
        return employeeDAO.getEmployeesByTeam(team);
    }

    public Employee createEmployee(Employee employee) {
        List<Employee> employees = getAllEmployee();
        Employee lastEmployee = employees.get(employees.size() - 1);
        employee.setId(lastEmployee.getId() + 1);
        return employeeDAO.createEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = getEmployeeByName(employee.getName());
        updatedEmployee.setTeam(employee.getTeam());
        return updatedEmployee;
    }

    public boolean deleteEmployee(Employee employee) {
        Employee deletedEmployee = getEmployeeByName(employee.getName());
        return employeeDAO.deleteEmployee(deletedEmployee);
    }
}
