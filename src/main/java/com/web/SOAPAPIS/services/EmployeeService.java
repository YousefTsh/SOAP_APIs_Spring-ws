package com.web.SOAPAPIS.services;

import allapis.soapapis.web.com.getemployee.EmployeeInfo;
import com.web.SOAPAPIS.model.Employee;

public interface EmployeeService {
    void AddEmployee (Employee employee);
    EmployeeInfo getEmployeeById(long employeeId);
    void updateEmployee(Employee employee);

    void deleteEmployee(long employeeId);
}
