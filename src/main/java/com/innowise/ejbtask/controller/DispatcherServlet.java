package com.innowise.ejbtask.controller;


import com.innowise.ejbtask.beans.BeanManager;
import com.innowise.ejbtask.wrapper.RequestAware;
import com.innowise.ejbtask.enums.Role;
import com.innowise.ejbtask.util.RequestUtil;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet(name = "main", value = "/")
public class DispatcherServlet extends HttpServlet {

    @EJB
    private BeanManager beanManager;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatch(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatch(req, resp);
    }


    public void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var beanType = beanType(req);
        var requestAware = RequestAware.aware(req.getServletContext(), req, resp);

        beanManager.executeBean(beanType, requestAware);
    }


    private String beanType(HttpServletRequest req) {
        return RequestUtil.path(req);
    }
}
