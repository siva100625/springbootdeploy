package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoute(){
        String result = employeeController.route();
        assertEquals("Welcome to SpringBoot Security", result);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1, emp2));

        List<RegisterDetails> result = employeeController.getMethod();

        assertEquals(2, result.size());
        verify(employeeService, times(1)).getMethod();
    }

    @Test
    void testGetEmployeeById(){
        RegisterDetails emp = new RegisterDetails();
        int empId = 1;
        when(employeeService.getEmployeeById(empId)).thenReturn(emp);

        RegisterDetails result = employeeController.getEmployeeById(empId);

        assertEquals(emp, result);
        verify(employeeService, times(1)).getEmployeeById(empId);
    }

    @Test
    void testPostMethod(){
        UserDetailsDto user = new UserDetailsDto();
        String expected = "Employee added successfully";
        when(employeeService.addNewEmployee(user)).thenReturn(expected);

        String result = employeeController.postMethod(user);

        assertEquals(expected, result);
        verify(employeeService, times(1)).addNewEmployee(user);
    }

    @Test
    void testPutMethod(){
        int empId = 1;
        String expected = "Employee updated successfully";
        when(employeeService.updateEmployee(empId)).thenReturn(expected);

        String result = employeeController.putMethod(empId);

        assertEquals(expected, result);
        verify(employeeService, times(1)).updateEmployee(empId);
    }

    @Test
    void testDeleteMethod(){
        int empId = 1;
        String expected = "Employee deleted successfully";
        when(employeeService.deleteEmployeeById(empId)).thenReturn(expected);

        String result = employeeController.deleteMethod(empId);

        assertEquals(expected, result);
        verify(employeeService, times(1)).deleteEmployeeById(empId);
    }
}
