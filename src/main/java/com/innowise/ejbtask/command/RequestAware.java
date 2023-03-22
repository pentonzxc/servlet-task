package com.innowise.ejbtask.command;

import com.innowise.ejbtask.beans.Bean;
import com.innowise.ejbtask.beans.data.EmptyData;
import com.innowise.ejbtask.enums.Role;
import com.innowise.ejbtask.util.RequestUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestAware {

    private ServletContext context;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public RequestAware(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }


    public static RequestAware aware(ServletContext context, HttpServletRequest req, HttpServletResponse resp) {
        return new RequestAware(context, req, resp);
    }

    public void forward(String target) throws ServletException, IOException {
        if (target == null || target.isEmpty()) {
            return;
        }

        target = String.format("/%s.jsp", target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }


    public void addAttribute(Bean.OutputData data) {
        if (data instanceof EmptyData || data.attributeName().isEmpty()) {
            return;
        }

        request.setAttribute(data.attributeName(), data.attributeValue());
    }

    public ServletContext getContext() {
        return context;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }



    public String authorize(String forward) {
        Role role = RequestUtil.role(request);

        if ("list".equals(forward) && role == Role.ADMIN) {
            forward = "adm-" + forward;
        }

        return forward;
    }
}

