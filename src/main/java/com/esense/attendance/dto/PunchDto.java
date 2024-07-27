package com.esense.attendance.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PunchDto {
    private Long id;

    private Timestamp start;

    private Timestamp end;


}
