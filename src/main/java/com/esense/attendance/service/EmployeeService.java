package com.esense.attendance.service;


import com.esense.attendance.entity.Employee;
import com.esense.attendance.exception.InvalidPasswordException;
import com.esense.attendance.exception.RegisteredEmailException;
import com.esense.attendance.mapper.EmployeeMapper;
import com.esense.attendance.repository.EmployeeRepository;
import com.esense.attendance.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CryptoService cryptoService;
    private final AuthService authService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CryptoService cryptoService, AuthService authService) {
        this.employeeRepository = employeeRepository;
        this.cryptoService = cryptoService;
        this.authService = authService;
    }

    public String login(String email, String password) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if (employee.isEmpty())
            throw new NoSuchElementException();

        if (!cryptoService.Hash(password).equals(employee.get().getPassword()))
            throw new InvalidPasswordException();

        return authService.generateEmployeeToken(EmployeeMapper.toEmployeeDto(employee.get()));
    }

    public void register(RegisterRequest registerRequest) {
         if (employeeRepository.findByEmail(registerRequest.email()).isPresent())
             throw new RegisteredEmailException();

        Employee employee = new Employee();
        employee.setFirstName(registerRequest.firstName());
        employee.setLastName(registerRequest.lastName());
        employee.setEmail(registerRequest.email());
        employee.setPassword(cryptoService.Hash(registerRequest.password()));
        employee.setPhoneNumber(registerRequest.phoneNumber());
        employee.setGender(registerRequest.gender());
        employee.setRole(registerRequest.role());
        employee.setDateOfBirth(registerRequest.dateOfBirth());
        employee.setJoiningDate(new Date());
        employeeRepository.save(employee);
    }
}
