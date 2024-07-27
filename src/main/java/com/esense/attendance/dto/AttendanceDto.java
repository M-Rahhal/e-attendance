package com.esense.attendance.dto;

import com.esense.attendance.entity.Punch;
import jakarta.websocket.server.ServerEndpoint;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AttendanceDto {

    private Long employeeID;

    private Date attendaceDate;

    private List<PunchDto> punchList;

}
