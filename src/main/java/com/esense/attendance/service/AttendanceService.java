package com.esense.attendance.service;


import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Attendance;
import com.esense.attendance.entity.Employee;
import com.esense.attendance.entity.key.AttendanceId;
import com.esense.attendance.exception.NoSuchRecordException;
import com.esense.attendance.exception.RegisteredAttendanceRecordException;
import com.esense.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeService employeeService;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository , EmployeeService employeeService) {
        this.attendanceRepository = attendanceRepository;
        this.employeeService = employeeService;
    }

    public Attendance addAttendanceRecord(EmployeeDto employeeDto){

        if (containsAttendanceRecord(employeeDto , Date.valueOf(LocalDate.now())))
            throw new RegisteredAttendanceRecordException();

        Employee employeeEntity = employeeService.getEmployeeById(employeeDto.getId());
        AttendanceId id = new AttendanceId(employeeDto.getId()  , Date.valueOf(LocalDate.now()));
        Attendance attendance = new Attendance(id ,employeeEntity , new ArrayList<>());
        attendanceRepository.save(attendance);

        return attendance;
    }

    public boolean containsAttendanceRecord(EmployeeDto employee , Date date){
        return attendanceRepository.findById(new AttendanceId(employee.getId(),date)).isPresent();
    }

    public Attendance getAttendanceRecord(EmployeeDto employeeDto , Date date){
        Optional<Attendance> attendance = attendanceRepository.findById(new AttendanceId(employeeDto.getId(),date));
        return attendance.orElse(null);
    }

    public void saveAttendanceRecord(Attendance attendance){
        this.attendanceRepository.save(attendance);
    }
}
