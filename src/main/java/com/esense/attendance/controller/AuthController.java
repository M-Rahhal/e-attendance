package com.esense.attendance.controller;


import com.esense.attendance.annotation.AdminAPI;
import com.esense.attendance.annotation.PublicAPI;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.request.LoginRequest;
import com.esense.attendance.request.RegisterRequest;
import com.esense.attendance.respose.GeneralResponse;
import com.esense.attendance.service.AuthService;
import com.esense.attendance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {


    private final EmployeeService employeeService;


    @Autowired
    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PublicAPI
    @PostMapping(path = "/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest request) {
        String token = employeeService.login(request.email() , request.password());
        Map<String , String> responsePayload = new HashMap<>();
        responsePayload.put("token", token);

        GeneralResponse response = new GeneralResponse(true , 200 , responsePayload);
        return ResponseEntity.ok(response);
    }

    @AdminAPI
    @PostMapping(path = "/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody RegisterRequest request) {
        employeeService.register(request);
        return ResponseEntity.ok(new GeneralResponse(true , 200 , null));
    }
}
