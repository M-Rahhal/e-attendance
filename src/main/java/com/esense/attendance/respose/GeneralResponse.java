package com.esense.attendance.respose;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record GeneralResponse(
        @JsonProperty("status") boolean status,
        @JsonProperty("status_code") int statusCode,
        @JsonProperty("map_properties") Map<String, Object> mapProperties
) {
}
