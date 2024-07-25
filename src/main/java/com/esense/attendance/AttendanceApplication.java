package com.esense.attendance;

import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Employee;
import com.esense.attendance.request.RegisterRequest;
import com.esense.attendance.service.CryptoService;
import com.esense.attendance.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@SpringBootApplication
public class AttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Welcome to Attendance Application");
			Calendar c = Calendar.getInstance();
			c.set(2001 , Calendar.NOVEMBER, 5);
			EmployeeService employeeService = ctx.getBean(EmployeeService.class);
			RegisterRequest registerRequest = new RegisterRequest("admin" , "admin"
			, "admin@esense.com", "admin", "123" ,
					"male" , "admin" ,Date.valueOf(LocalDate.of(2001 , 11, 5)));

			employeeService.register(registerRequest);
		};
	}
}
