package com.esense.attendance.controller;


import com.esense.attendance.annotation.EmployeeAPI;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.respose.GeneralResponse;
import com.esense.attendance.service.PunchService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/punch")
public class PunchController {

    private final PunchService punchService;

    @Autowired
    public PunchController(PunchService punchService) {
        this.punchService = punchService;
    }

    @EmployeeAPI
    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> punch(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("employee");
        punchService.createPunch(employeeDto);
        return ResponseEntity.ok(new GeneralResponse(true, 200 , null));
    }

}
