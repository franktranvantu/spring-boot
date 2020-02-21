package com.frank.springboot.springmvc.controller;

import com.frank.springboot.springmvc.model.Employee;
import com.frank.springboot.springmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ModelAndView getAllEmployees() {
        ModelAndView mv = new ModelAndView("employees");
        List<Employee> employees = employeeService.getAllEmployee();
        mv.addObject("employees", employees);
        return mv;
    }

    @PostMapping(path = "/create-employee", consumes = "application/x-www-form-urlencoded")
    public ModelAndView createEmployee(Employee employee) {
        ModelAndView mv = new ModelAndView("employees");
        employeeService.createEmployee(employee);
        List<Employee> employees = employeeService.getAllEmployee();
        mv.addObject("employees", employees);
        mv.addObject("employee", null);
        return mv;
    }

    @GetMapping("/find-employee-by-name")
    public ModelAndView getEmployeesByName(String name) {
        ModelAndView mv = new ModelAndView("employees");
        Employee employee = employeeService.getEmployeeByName(name);
        List<Employee> employees = null;
        if (employee != null) {
            employees = Arrays.asList(employee);
        }
        mv.addObject("employees", employees);
        mv.addObject("employee", employee);
        return mv;
    }

    @PostMapping(path = "/update-employee", consumes = "application/x-www-form-urlencoded")
    public ModelAndView updateEmployee(Employee employee) {
        ModelAndView mv = new ModelAndView("employees");
        employeeService.updateEmployee(employee);
        List<Employee> employees = employeeService.getAllEmployee();
        mv.addObject("employees", employees);
        mv.addObject("employee", null);
        return mv;
    }

    @PostMapping(path = "/delete-employee", consumes = "application/x-www-form-urlencoded")
    public ModelAndView deleteEmployee(Employee employee) {
        ModelAndView mv = new ModelAndView("employees");
        employeeService.deleteEmployee(employee);
        List<Employee> employees = employeeService.getAllEmployee();
        mv.addObject("employees", employees);
        mv.addObject("employee", null);
        return mv;
    }

    @GetMapping("/find-employees-by-team")
    public ModelAndView getEmployeesByTeam(String team) {
        ModelAndView mv = new ModelAndView("employees");
        List<Employee> employees = employeeService.getEmployeesByTeam(team);
        mv.addObject("employees", employees);
        return mv;
    }
}
