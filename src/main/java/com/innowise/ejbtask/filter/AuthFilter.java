package com.innowise.ejbtask.filter;


import com.innowise.ejbtask.enums.Role;
import com.innowise.ejbtask.repository.UserRepository;
import com.innowise.ejbtask.util.RequestUtil;
import jakarta.ejb.EJB;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.innowise.ejbtask.util.PageConstants.PERMIT_ALL;

@WebFilter(filterName = "someFilter", urlPatterns = "/*")
public class AuthFilter implements jakarta.servlet.Filter {

    @EJB
    private UserRepository repository;


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (isSecureEndpoint(request)) {
            Role role = RequestUtil.role(request);
            String username = RequestUtil.username(request);

            if (role == Role.NONE) {
                servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
                return;
            }

            if(username == null || repository.findByName(username).isEmpty()){
                HttpSession session = request.getSession(false);

                session.removeAttribute("role");
                session.removeAttribute("username");
            }

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private static boolean isSecureEndpoint(HttpServletRequest request) {
        String path = RequestUtil.path(request);

        for (String s : PERMIT_ALL) {
            if (s.equals(path)) return false;
        }

        return true;
    }
}
