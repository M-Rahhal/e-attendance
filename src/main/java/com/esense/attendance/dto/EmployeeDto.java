package com.esense.attendance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
}
