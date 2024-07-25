package com.esense.attendance.service;


import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        String jsonEmployee =  claims.get("employee", String.class);
        return new GsonBuilder().setDateFormat("MM-dd-yyyy").create().fromJson(jsonEmployee, EmployeeDto.class);
    }

    public String generateEmployeeToken(EmployeeDto employee){
        Map<String, Object> claims = new HashMap<>();
        Gson gson = new GsonBuilder()
                .setDateFormat("MM-dd-yyyy").create();
        claims.put("employee" , gson.toJson(employee));
        return jwtService.generateToken("esense-attendance-system" , claims);
    }
}
