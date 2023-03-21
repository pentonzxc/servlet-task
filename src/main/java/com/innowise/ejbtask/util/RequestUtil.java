package com.innowise.ejbtask.util;

import com.innowise.ejbtask.security.Role;
import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {
    private RequestUtil() {
    }

    public static String path(HttpServletRequest request) {
        return request.getServletPath().substring(1);
    }


    public static Role role(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("role");

        return role != null ? Role.valueOf(role) : Role.NONE;
    }
}
