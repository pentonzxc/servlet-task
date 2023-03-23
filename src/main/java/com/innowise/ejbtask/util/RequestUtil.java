package com.innowise.ejbtask.util;

import com.innowise.ejbtask.enums.Role;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.innowise.ejbtask.util.PageConstants.ROLE_DEPENDS_PAGES;

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


    public static String username(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("username");
    }


    public static Map<String, String> query(HttpServletRequest request) {
        var map = new HashMap<String, String>();

        for (var str : request.getQueryString().split("&")) {
            var arr = str.split("=");
            map.put(arr[0], arr[1]);
        }

        return map;
    }


    public static boolean isRoleDependsPage(String page) {
        for (String s : ROLE_DEPENDS_PAGES) {
            if (s.equals(page)) return true;
        }

        return false;
    }
}
