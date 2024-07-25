package com.esense.attendance.filter;


import com.esense.attendance.annotation.AdminAPI;
import com.esense.attendance.annotation.EmployeeAPI;
import com.esense.attendance.annotation.PublicAPI;
import com.esense.attendance.dto.EmployeeDto;
import com.esense.attendance.exception.UnauthorizedException;
import com.esense.attendance.exception.UnsupportedHandlerException;
import com.esense.attendance.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.Objects;


@Component
@Order(1)
public class AuthFilter implements Filter {



    private final AuthService authService;

    private final ApplicationContext applicationContext;

    @Autowired
    public AuthFilter(AuthService authService, ApplicationContext applicationContext) {
        this.authService = authService;
        this.applicationContext = applicationContext;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException{

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Origin" , "*");

        HandlerMethod handlerMethod = null;
        try {

            RequestMappingHandlerMapping req2HandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
            HandlerExecutionChain handlerExeChain = req2HandlerMapping.getHandler(req);


            if (Objects.nonNull(handlerExeChain)) {

                handlerMethod = (HandlerMethod) handlerExeChain.getHandler();

                if (!handlerMethod.getBeanType().getName().startsWith("com.esense"))
                    throw new UnsupportedHandlerException();

                PublicAPI isPublic = handlerMethod.getMethod().getAnnotation(PublicAPI.class);
                AdminAPI isAdmin = handlerMethod.getMethod().getAnnotation(AdminAPI.class);
                EmployeeAPI isEmployee = handlerMethod.getMethod().getAnnotation(EmployeeAPI.class);

                if (isPublic != null) {
                    chain.doFilter(request, response);
                    return;
                }

                if (isAdmin != null) {

                    String token = req.getHeader("Authorization");

                    if (token == null || !token.startsWith("Bearer "))
                        throw new UnauthorizedException();
                    token = token.substring(7);
                    EmployeeDto employee = authService.verifyEmployeeToken(token);

                    if (employee == null)
                        throw new UnauthorizedException();

                    if (!employee.getRole().equals("admin"))
                        throw new UnauthorizedException();

                    req.setAttribute("employee", employee);
                    chain.doFilter(request, response);
                    return;
                }

                if (isEmployee != null) {
                    String token = req.getHeader("Authorization");

                    if (token == null || !token.startsWith("Bearer "))
                        throw new UnauthorizedException();

                    token = token.substring(7);
                    EmployeeDto employee = authService.verifyEmployeeToken(token);

                    if (employee == null)
                        throw new UnauthorizedException();

                    req.setAttribute("employee", employee);
                    chain.doFilter(request, response);
                    return;
                }

            }
            throw new UnsupportedHandlerException();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}