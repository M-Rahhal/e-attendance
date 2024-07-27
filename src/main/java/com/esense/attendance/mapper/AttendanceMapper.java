package com.esense.attendance.mapper;

import com.esense.attendance.dto.AttendanceDto;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Attendance;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AttendanceMapper {
    public static AttendanceDto toDto(Attendance entity){
        return AttendanceDto.builder()
                .attendaceDate(entity.getId().getAttendanceDate())
                .employeeID(entity.getId().getEmployeeId())
                .punchList(entity.getPunches().stream().map(PunchMapper::toDto).collect(Collectors.toList()))
                .build();
    }
}
