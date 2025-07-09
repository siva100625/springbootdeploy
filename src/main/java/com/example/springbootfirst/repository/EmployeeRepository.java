package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findReferenceByempId(int id);

    List<Employee> findByjob(String job);

    List<Employee> findByname(String name);
}
