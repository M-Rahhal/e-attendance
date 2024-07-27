package com.esense.attendance.controller;


import com.esense.attendance.annotation.EmployeeAPI;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.mapper.EmployeeMapper;
import com.esense.attendance.respose.GeneralResponse;
import com.esense.attendance.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/attendance")
public class AttendanceController {


    @Autowired
    EmployeeService employeeService;

    @EmployeeAPI
    @GetMapping("/all-employees")
    public ResponseEntity<GeneralResponse> getAllEmployeesLastAttendance() {
        Map<String, String > map = new HashMap<>();
        map.put("employees" , employeeService.getAllEmployeesWithLastAttendanceRecord().toString());
        return ResponseEntity.ok(new GeneralResponse(true , 200 , map));
    }


    @EmployeeAPI
    @GetMapping("/employee")
    public ResponseEntity<GeneralResponse> getEmployeeAttendance(HttpServletRequest request) {
        Map<String, String > map = new HashMap<>();
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("employee");
        map.put("employees" , EmployeeMapper.toDto(
                employeeService.getEmployeeById(employeeDto.getId()), employeeService.getEmployeeById(employeeDto.getId()).getAttendances()
        ).toString());
        return ResponseEntity.ok(new GeneralResponse(true , 200 , map));
    }


}
