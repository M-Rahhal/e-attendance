package com.esense.attendance.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.util.Date;

public record RegisterRequest(
             @JsonProperty("first_name") String firstName,
             @JsonProperty("last_name") String lastName,
             @JsonProperty("email") String email,
             @JsonProperty("password") String password,
             @JsonProperty("phone_number") String phoneNumber,
             @JsonProperty("gender") String gender,
             @JsonProperty("role") String role,
             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
             @JsonProperty("date_of_birth") Date dateOfBirth
) {
}
