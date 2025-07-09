package com.example.springbootfirst.services;


import com.example.springbootfirst.models.Employee;
import com.example.springbootfirst.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloWorldService {

    @Autowired
    EmployeeRepository empRepo;
    public List<Employee> getMethod() {
        return empRepo.findAll();
    }

    public Employee getEmployeeById(int empId) {
        return empRepo.findReferenceByempId(empId);
    }

    public String postMethod(Employee employee) {
         empRepo.save(employee);
         return "Employee added successfully";
    }

    public List<Employee> getbyjob(String job) {
        return empRepo.findByjob(job);
    }

    public List<Employee> getbyname(String name) {
        return empRepo.findByname(name);

    }

    public String putMethodById(Employee employee, int id) {
        empRepo.save(employee);
        return "Employee updated successfully";
    }

    public String deleteMethod(int id) {
        empRepo.deleteById(id);
        return "Employee deleted successfully";
    }
}
