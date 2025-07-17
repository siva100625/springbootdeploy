package com.example.springbootfirst.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private int empId;
    private String name;
    private String email;
    private String password;
    private String userName;
    private Set<String> roleNames;

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }
}
