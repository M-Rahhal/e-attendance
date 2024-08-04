package com.esense.attendance.controller;
import com.esense.attendance.annotation.EmployeeAPI;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.request.GetAttendanceByWeekRequest;
import com.esense.attendance.service.ReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@RestController
@RequestMapping("/e-attendance")
public class ReportController {

    @Autowired
    private ReportService jasperService;



    @EmployeeAPI
    @PostMapping(path = "/report")
    public void generateReport(@RequestBody GetAttendanceByWeekRequest body, HttpServletRequest request , HttpServletResponse response) throws JRException, SQLException, IOException, ParseException {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("employee");
        jasperService.generateProductsReportAsPdfStream(
                employeeDto.getId(),
                employeeDto.getFirstName()+" "+employeeDto.getLastName(),
                employeeDto.getEmail(),
                new Date(body.startDate().getTime()),
                new Date(body.endDate().getTime()),
                response);
    }
}