package com.websystique.springmvc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.websystique.springmvc.service.CentrifyOAuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class ApiAuthenticationFilter extends GenericFilterBean {

    public CentrifyOAuthService centrifyOAuthService;

    public ApiAuthenticationFilter(CentrifyOAuthService centrifyOAuthService) {
        this.centrifyOAuthService = centrifyOAuthService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String emailId = request.getParameter("emailId");
        String password = request.getParameter("password");
        System.out.println(emailId);
        System.out.println(password);
        if (!StringUtils.isEmpty(emailId) && !StringUtils.isEmpty(password)) {
            chain.doFilter(request, response);
        } else {

        }
    }

}