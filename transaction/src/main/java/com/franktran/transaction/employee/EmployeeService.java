package com.franktran.transaction.employee;

import com.franktran.transaction.department.Department;
import com.franktran.transaction.department.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public String createEmployee(EmployeeRequestVO employeeRequest) {
        Department department = new Department();
        department.setName(employeeRequest.getDepartmentName());
        department = departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName(employeeRequest.getEmployeeName());
        employee.setDepartmentId(department.getId());
        employeeRepository.save(employee);

        return "Success";
    }
}
