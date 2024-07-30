package com.esense.attendance.controller;


import com.esense.attendance.annotation.EmployeeAPI;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.respose.GeneralResponse;
import com.esense.attendance.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @EmployeeAPI
    @GetMapping("/all-employees")
    public ResponseEntity<GeneralResponse> getAllEmployeesLastAttendance(HttpServletRequest request) {
        Map<String, Object > map = new HashMap<>();
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("employee");
        map.put("employee" , employeeDto);
        return ResponseEntity.ok(new GeneralResponse(true , 200 , map));
    }
}
