package com.esense.attendance.service;


import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Employee;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {


    private final JwtService jwtService;

    @Autowired
    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public EmployeeDto verifyEmployeeToken(String token){
        Claims claims = jwtService.parseToken(token);
        return claims.get("employee", EmployeeDto.class);
    }

    public String generateEmployeeToken(EmployeeDto employee){
        Map<String, Object> claims = new HashMap<>();
        claims.put("employee", employee);
        return jwtService.generateToken("esense-attendance-system" , claims);
    }
}
