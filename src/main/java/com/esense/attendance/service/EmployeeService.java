package com.esense.attendance.service;


import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.entity.Attendance;
import com.esense.attendance.entity.Employee;
import com.esense.attendance.entity.key.AttendanceId;
import com.esense.attendance.exception.InvalidPasswordException;
import com.esense.attendance.exception.NoSuchRecordException;
import com.esense.attendance.exception.RegisteredEmailException;
import com.esense.attendance.mapper.EmployeeMapper;
import com.esense.attendance.repository.AttendanceRepository;
import com.esense.attendance.repository.EmployeeRepository;
import com.esense.attendance.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CryptoService cryptoService;
    private final AuthService authService;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CryptoService cryptoService, AuthService authService, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.cryptoService = cryptoService;
        this.authService = authService;
        this.attendanceRepository = attendanceRepository;
    }

    public String login(String email, String password) {

        Optional<Employee> employee = employeeRepository.findByEmail(email);

        if (employee.isEmpty())
            throw new NoSuchElementException();

        if (!cryptoService.Hash(password).equals(employee.get().getPassword()))
            throw new InvalidPasswordException();

        return authService.generateEmployeeToken(EmployeeMapper.toDto(employee.get()));
    }

    public void register(RegisterRequest registerRequest) {

         if (employeeRepository.findByEmail(registerRequest.email()).isPresent())
             throw new RegisteredEmailException();

        Employee employee = new Employee();
        employee.setFirstName(registerRequest.firstName());
        employee.setLastName(registerRequest.lastName());
        employee.setEmail(registerRequest.email());
        employee.setPassword(cryptoService.Hash(registerRequest.password()));
        employee.setPhoneNumber(registerRequest.phoneNumber());
        employee.setGender(registerRequest.gender());
        employee.setRole(registerRequest.role());
        employee.setDateOfBirth(registerRequest.dateOfBirth());
        employee.setJoiningDate(Date.valueOf(LocalDate.now()));

        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.orElseThrow(NoSuchRecordException::new);
    }
    public Employee getEmployeeByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        return employee.orElseThrow(NoSuchRecordException::new);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<EmployeeDto> findAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::toDto).collect(Collectors.toList());
    }

    public List<EmployeeDto> getAllEmployeesWithLastAttendanceRecord(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> {
            Date lastAttendaceDate = attendanceRepository.findNewestDate(employee.getId());
            Attendance lasAttendance = attendanceRepository.findById(new AttendanceId(employee.getId() , lastAttendaceDate)).get();
            return EmployeeMapper.toDto(employee, List.of(lasAttendance));
        }).collect(Collectors.toList());
    }
}
