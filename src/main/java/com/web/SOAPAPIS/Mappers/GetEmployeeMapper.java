package com.web.SOAPAPIS.Mappers;

import allapis.soapapis.web.com.getemployee.EmployeeInfo;
import com.web.SOAPAPIS.model.Employee;

public class GetEmployeeMapper {

    public static EmployeeInfo employeeInfoMapper(Employee employee){
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setEmployeeId(employee.getEmployeeId());
        employeeInfo.setAddress(employee.getAddress());
        employeeInfo.setDepartment(employee.getDepartment());
        employeeInfo.setName(employee.getName());
        employeeInfo.setPhone(employee.getPhone());
        return employeeInfo;
    }
}
