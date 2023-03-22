package com.innowise.ejbtask;


import com.innowise.ejbtask.enums.Role;
import com.innowise.ejbtask.util.RequestUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "someFilter", urlPatterns = "/*")
public class AuthFilter implements jakarta.servlet.Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!"register".equals(RequestUtil.path(request)) &&
                !"login".equals(RequestUtil.path(request)) &&
                !"register.jsp".equals(RequestUtil.path(request)) &&
                !"index.jsp".equals(RequestUtil.path(request)) &&
                !"login.jsp".equals(RequestUtil.path(request)) &&
                !"index".equals(RequestUtil.path(request))) {

            Role role = RequestUtil.role(request);

            if (role == Role.NONE) {
                servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
