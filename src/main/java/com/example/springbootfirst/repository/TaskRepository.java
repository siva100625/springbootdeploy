package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.Task;
import com.example.springbootfirst.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByEmployee(RegisterDetails employee);
    List<Task> findByEmployeeEmpId(int empId);
} 