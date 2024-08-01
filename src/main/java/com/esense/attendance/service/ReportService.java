package com.esense.attendance.service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


@Service
public class ReportService {

    private static final String JASPER_FILE_PATH  = "src/main/resources/templates/jasper/Blank_A4_2.jasper";

    @Value("${spring.datasource.url}")
    private String connectionUrl;

    @Value("${spring.datasource.username}")
    private String connectionUsername;

    @Value("${spring.datasource.password}")
    private String connectionPassword;

    @Autowired
    private DataSource dataSource;




    public void generateProductsReport(Long id , Date startDate , Date endDate , HttpServletResponse response) throws JRException, SQLException, IOException {

        Connection connection = dataSource.getConnection();
        HashMap<String , Object> map = new HashMap<>();
        map.put("id" , id);
        map.put("start_date" , startDate);
        map.put("end_date" , endDate);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
        JasperPrint jasperPrint =  JasperFillManager.fillReport(JASPER_FILE_PATH, new HashMap<>(), connection);
        JasperExportManager.exportReportToPdfStream(jasperPrint , response.getOutputStream());
    }
}