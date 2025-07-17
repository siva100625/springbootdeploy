package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    RegisterDetailsRepository registerDetailsRepository;

    @Mock
    RolesRepository rolesRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(registerDetailsRepository.findAll()).thenReturn(Arrays.asList(emp1, emp2));

        List<RegisterDetails> result = employeeService.getMethod();

        assertEquals(2, result.size());
        verify(registerDetailsRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById_Found(){
        RegisterDetails emp = new RegisterDetails();
        emp.setEmpId(1);
        when(registerDetailsRepository.findById(1)).thenReturn(Optional.of(emp));

        RegisterDetails result = employeeService.getEmployeeById(1);

        assertEquals(emp, result);
    }

    @Test
    void testGetEmployeeById_NotFound(){
        when(registerDetailsRepository.findById(999)).thenReturn(Optional.empty());

        RegisterDetails result = employeeService.getEmployeeById(999);

        assertNotNull(result); // Since your method returns new RegisterDetails() when not found
    }

    @Test
    void testUpdateEmployee_Success(){
        RegisterDetails emp = new RegisterDetails();
        emp.setEmpId(10);
        when(registerDetailsRepository.findById(10)).thenReturn(Optional.of(emp));

        String result = employeeService.updateEmployee(10);

        assertEquals("Employee Updated Successfully", result);
        verify(registerDetailsRepository, times(1)).save(emp);
    }

    @Test
    void testUpdateEmployee_UserNotFound(){
        when(registerDetailsRepository.findById(999)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(999);
        });

        assertTrue(exception.getMessage().contains("No Such User Present"));
    }

    @Test
    void testDeleteEmployeeById(){
        int empId = 5;

        String result = employeeService.deleteEmployeeById(empId);

        assertEquals("Employee Deleted Successfully", result);
        verify(registerDetailsRepository, times(1)).deleteById(empId);
    }

    @Test
    void testAddNewEmployee_Success(){
        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmpId(1);
        dto.setName("Test");
        dto.setEmail("test@example.com");
        dto.setUserName("testuser");
        dto.setPassword("pass");
        dto.setRoleNames((Set<String>) Arrays.asList("USER", "ADMIN"));

        Roles userRole = new Roles();
        userRole.setRoleName("USER");
        Roles adminRole = new Roles();
        adminRole.setRoleName("ADMIN");

        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(rolesRepository.findByRoleName("USER")).thenReturn(Optional.of(userRole));
        when(rolesRepository.findByRoleName("ADMIN")).thenReturn(Optional.of(adminRole));

        String result = employeeService.addNewEmployee(dto);

        assertEquals("Employee Added Successfully", result);
        verify(registerDetailsRepository, times(1)).save(any(RegisterDetails.class));
    }

    @Test
    void testAddNewEmployee_RoleNotFound(){
        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmpId(1);
        dto.setName("Test");
        dto.setEmail("test@example.com");
        dto.setUserName("testuser");
        dto.setPassword("pass");
        dto.setRoleNames((Set<String>) Collections.singletonList("UNKNOWN"));

        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(rolesRepository.findByRoleName("UNKNOWN")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.addNewEmployee(dto);
        });

        assertTrue(exception.getMessage().contains("User not found"));
    }
}
