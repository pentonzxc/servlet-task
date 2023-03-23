package com.innowise.ejbtask.wrapper;

import com.innowise.ejbtask.beans.data.EmptyData;
import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.enums.Role;
import com.innowise.ejbtask.util.PageConstants;
import com.innowise.ejbtask.util.RequestUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.innowise.ejbtask.util.PageConstants.ADMIN_PREFIX;
import static com.innowise.ejbtask.util.RequestUtil.isRoleDependsPage;

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


    public void redirect(String target) {
        if (target == null || target.isEmpty()) {
            return;
        }

        try {
            response.sendRedirect(target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void addAttribute(RequestBean.OutputData data) {
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


    public String authorize(String page) {
        Role role = RequestUtil.role(request);

        if (isRoleDependsPage(page) && role == Role.ADMIN) {
            page = ADMIN_PREFIX + page;
        }

        return page;
    }
}

