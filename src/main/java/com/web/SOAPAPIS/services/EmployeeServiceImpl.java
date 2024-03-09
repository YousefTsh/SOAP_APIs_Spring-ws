package com.web.SOAPAPIS.services;

import allapis.soapapis.web.com.getemployee.EmployeeInfo;
import com.web.SOAPAPIS.Mappers.GetEmployeeMapper;
import com.web.SOAPAPIS.model.Employee;
import com.web.SOAPAPIS.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public void AddEmployee(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public EmployeeInfo getEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findEmployeeByEmployeeId(employeeId);
        return GetEmployeeMapper.employeeInfoMapper(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
