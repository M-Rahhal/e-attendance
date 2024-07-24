package com.esense.attendance.repository;

import com.esense.attendance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Optional<Employee> findByEmail(String email);
    public Optional<Employee> findByEmailAndPassword(String email, String password);
}
