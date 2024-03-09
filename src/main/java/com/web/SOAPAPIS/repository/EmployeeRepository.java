package com.web.SOAPAPIS.repository;

import com.web.SOAPAPIS.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findEmployeeByEmployeeId(long employeeId);
}
