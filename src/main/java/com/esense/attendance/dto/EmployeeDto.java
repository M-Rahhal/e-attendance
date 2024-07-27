package com.esense.attendance.dto;

import com.esense.attendance.entity.Attendance;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EmployeeDto {

    private Long id;


    private String firstName;


    private String lastName;


    private String email;


    private String phoneNumber;


    private String gender;


    private String role;


    private Date joiningDate;


    private Date dateOfBirth;

    private List<AttendanceDto> attendanceList;
}
