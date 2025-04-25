package com.example.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore

    private Department department;

    private BigDecimal salary;

    public Employee() {}

    public Employee(String Name, String email, String phoneNumber, Department department, BigDecimal salary) {
        this.Name = Name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return Name; }

    public void setName(String Name) { this.Name = Name; }


    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Department getDepartment() { return department; }

    public void setDepartment(Department department) { this.department = department; }

    public BigDecimal getSalary() { return salary; }

    public void setSalary(BigDecimal salary) { this.salary = salary; }
}
