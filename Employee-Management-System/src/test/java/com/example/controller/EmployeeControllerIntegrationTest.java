package com.example.controller;

import com.example.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("Jane Doe");

        ResponseEntity<Employee> response = restTemplate.postForEntity("/api/employees", employee, Employee.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Jane Doe", response.getBody().getName());
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setName("Sai");

        restTemplate.postForEntity("/api/employees", employee1, Employee.class);

        ResponseEntity<List> response = restTemplate.exchange("/api/employees", HttpMethod.GET, null, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setName("Arthi");

        ResponseEntity<Employee> createdEmployee = restTemplate.postForEntity("/api/employees", employee, Employee.class);
        Long employeeId = createdEmployee.getBody().getId();

        ResponseEntity<Employee> response = restTemplate.getForEntity("/api/employees/" + employeeId, Employee.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Arthi", response.getBody().getName());
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setName("Bhanu");

        ResponseEntity<Employee> createdEmployee = restTemplate.postForEntity("/api/employees", employee, Employee.class);
        Long employeeId = createdEmployee.getBody().getId();

        employee.setName("Updated Bhanu");
        restTemplate.put("/api/employees/" + employeeId, employee);

        ResponseEntity<Employee> updatedEmployee = restTemplate.getForEntity("/api/employees/" + employeeId, Employee.class);
        assertEquals(HttpStatus.OK, updatedEmployee.getStatusCode());
        assertEquals("Updated Bhanu", updatedEmployee.getBody().getName());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setName("DeleteMe");

        ResponseEntity<Employee> createdEmployee = restTemplate.postForEntity("/api/employees", employee, Employee.class);
        Long employeeId = createdEmployee.getBody().getId();

        ResponseEntity<String> response = restTemplate.exchange(
            "/api/employees/" + employeeId, HttpMethod.DELETE, null, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee deleted successfully.", response.getBody());
    }
}
