package com.example.service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertEquals("John Doe", savedEmployee.getName());
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setName("Sai");

        Employee employee2 = new Employee();
        employee2.setName("Arthi");

        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setName("Shivathmika");

        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);
        assertEquals("Shivathmika", foundEmployee.getName());
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(1L);
        });
    }

    @Test
    void testUpdateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setName("Bhanu");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Updated Bhanu");

        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);
        assertEquals("Updated Bhanu", result.getName());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setName("Arthi");

        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));

        employeeService.deleteEmployee(1L);
        Mockito.verify(employeeRepository, Mockito.times(1)).delete(employee);
    }
}
