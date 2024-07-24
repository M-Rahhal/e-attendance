package com.esense.attendance.mapper;

import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Employee;
import org.springframework.stereotype.Component;


public class EmployeeMapper {
    public static EmployeeDto toEmployeeDto(Employee entity) {
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
}
