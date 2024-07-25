package com.esense.attendance.service;


import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Attendance;
import com.esense.attendance.entity.Employee;
import com.esense.attendance.entity.Punch;
import com.esense.attendance.repository.PunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Service
public class PunchService {

    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;


    private final PunchRepository punchRepository;;

    @Autowired
    public PunchService(AttendanceService attendanceService, EmployeeService employeeService , PunchRepository punchRepository) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
        this.punchRepository = punchRepository;
    }

    public void createPunch(EmployeeDto employeeDto) {

        Employee employeeEntity = employeeService.getEmployeeById(employeeDto.getId());
        Attendance attendanceEntity = (attendanceService.containsAttendanceRecord(employeeDto , Date.valueOf(LocalDate.now())))?
                attendanceService.getAttendanceRecord(employeeDto , Date.valueOf(LocalDate.now())):
                attendanceService.addAttendanceRecord(employeeDto);

        if (employeeEntity.getActivePunch() == null){

            Punch punchEntity = new Punch();
            punchEntity.setAttendance(attendanceEntity);
            punchEntity.setStart(new Timestamp(System.currentTimeMillis()));
            punchRepository.save(punchEntity);


            employeeEntity.setActivePunch(punchEntity);
            employeeService.saveEmployee(employeeEntity);

            attendanceEntity.addPunch(punchEntity);
            attendanceService.saveAttendanceRecord(attendanceEntity);
        }
        else {
            Punch punchEntity = employeeEntity.getActivePunch();
            punchEntity.setEnd(new Timestamp(System.currentTimeMillis()));
            punchRepository.save(punchEntity);

            employeeEntity.setActivePunch(null);
            employeeService.saveEmployee(employeeEntity);
        }

    }
}
