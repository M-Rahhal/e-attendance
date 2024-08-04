package com.esense.attendance.service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;


@Service
public class ReportService {

    private static final String JASPER_FILE_PATH  = "C:\\Users\\MRahhal\\Downloads\\attendance\\attendance\\src\\main\\resources\\templates\\jasper\\report.jrxml";


    @Autowired
    private DataSource dataSource;



    public void generateProductsReportAsPdfStream(Long id , String fullName , String email , Date startDate , Date endDate , HttpServletResponse response) throws JRException, SQLException, IOException {

        Connection connection = dataSource.getConnection();
        HashMap<String , Object> map = new HashMap<>();
        map.put("id" , id);
        map.put("start_date" , startDate);
        map.put("end_date" , endDate);
        map.put("title" , "Attendance Report");
        map.put("EmployeeName" , fullName);
        map.put("email" , email);
        response.setContentType("application/pdf");
        JasperReport report = JasperCompileManager.compileReport(JASPER_FILE_PATH);
        JasperPrint jasperPrint =  JasperFillManager.fillReport(report, map, connection);
        JasperExportManager.exportReportToPdfStream(jasperPrint , response.getOutputStream());
    }

}