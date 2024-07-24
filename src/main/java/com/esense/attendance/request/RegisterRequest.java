package com.esense.attendance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.util.Date;

public record RegisterRequest(
             @JsonProperty("first_name") String firstName,
             @JsonProperty("last_name") String lastName,
             @JsonProperty("email") String email,
             @JsonProperty("paswword") String password,
             @JsonProperty("phone_number") String phoneNumber,
             @JsonProperty("gender") String gender,
             @JsonProperty("role") String role,
             @JsonProperty("joining_date") Date joiningDate,
             @JsonProperty("date_of_birth") Date dateOfBirth
) {
}
