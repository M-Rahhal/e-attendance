package com.esense.attendance.controller;


import com.esense.attendance.annotation.EmployeeAPI;
import com.esense.attendance.dto.AttendanceDto;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.mapper.EmployeeMapper;
import com.esense.attendance.request.GetAttendanceByWeekRequest;
import com.esense.attendance.respose.GeneralResponse;
import com.esense.attendance.service.AttendanceService;
import com.esense.attendance.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/attendance")
public class AttendanceController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    @EmployeeAPI
    @GetMapping("/all-employees")
    public ResponseEntity<GeneralResponse> getAllEmployeesLastAttendance() {
        Map<String, Object > map = new HashMap<>();
        map.put("employees" , employeeService.getAllEmployeesWithLastAttendanceRecord());
        return ResponseEntity.ok(new GeneralResponse(true , 200 , map));
    }


    @EmployeeAPI
    @PostMapping("/employee/week")
    public ResponseEntity<GeneralResponse> getEmployeeAttendance(@RequestBody GetAttendanceByWeekRequest body, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("employee");
        List<AttendanceDto> attendanceDtoList = attendanceService.findAttendancesByWeek( new java.sql.Date(body.startDate().getTime()),new java.sql.Date(body.endDate().getTime()), employeeDto.getId());
        map.put("attendance" , attendanceDtoList);
        return ResponseEntity.ok(new GeneralResponse(true , 200 , map));
    }


}
