package com.web.SOAPAPIS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID=1;

    @Id
    @GeneratedValue
    private long employeeId;

    private String name;

    private String department;

    private String phone;

    private String address;
}
