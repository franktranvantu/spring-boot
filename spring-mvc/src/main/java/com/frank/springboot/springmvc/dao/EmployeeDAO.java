package com.frank.springboot.springmvc.dao;

import com.frank.springboot.springmvc.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDAO {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeDAO() {
        employees.add(new Employee(1, "Frank", "DEV"));
        employees.add(new Employee(2, "Patrik", "DEV"));
        employees.add(new Employee(3, "June", "HR"));
        employees.add(new Employee(4, "Martin", "IT"));
        employees.add(new Employee(5, "Kelly", "SA"));
        employees.add(new Employee(6, "Tracy", "QA"));
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeByName(String name) {
        return employees.stream().filter(employee -> employee.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Employee> getEmployeesByTeam(String team) {
        if (StringUtils.isEmpty(team)) {
            return employees;
        }
        return employees.stream().filter(employee -> employee.getTeam().equals(team)).collect(Collectors.toList());
    }

    public Employee createEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public boolean deleteEmployee(Employee employee) {
        employees.remove(employee);
        return true;
    }

}
