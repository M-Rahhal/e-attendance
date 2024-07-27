package com.esense.attendance.mapper;


import com.esense.attendance.controller.PunchController;
import com.esense.attendance.dto.PunchDto;
import com.esense.attendance.entity.Punch;

public class PunchMapper {

    public static PunchDto toDto(Punch entity){
        return PunchDto.builder()
                .id(entity.getId())
                .end(entity.getEnd())
                .start(entity.getStart())
                .build();
    }
}
