package com.esense.attendance.mapper;

import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Attendance;
import com.esense.attendance.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;


public class EmployeeMapper {
    public static EmployeeDto toDto(Employee entity) {
        return EmployeeDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .gender(entity.getGender())
                .joiningDate(entity.getJoiningDate())
                .dateOfBirth(entity.getDateOfBirth())
                .build();
    }

    public static EmployeeDto toDto(Employee entity , List<Attendance> attendanceList) {
        return EmployeeDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .gender(entity.getGender())
                .joiningDate(entity.getJoiningDate())
                .dateOfBirth(entity.getDateOfBirth())
                .attendanceList(attendanceList.stream().map(
                        AttendanceMapper::toDto
                ).collect(Collectors.toList()))
                .build();
    }
}
