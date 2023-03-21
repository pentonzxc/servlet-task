package com.innowise.ejbtask.mapper;

import com.innowise.ejbtask.beans.Bean;
import com.innowise.ejbtask.beans.LoginBean;
import com.innowise.ejbtask.beans.RegisterBean;
import com.innowise.ejbtask.beans.data.EmptyData;
import com.innowise.ejbtask.command.RequestAware;

public class InputDataMapper {

    public static Bean.InputData toRegisterBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();

        return new RegisterBean.RegisterData(req.getParameter("username"), req.getParameter("password"));
    }


    public static Bean.InputData toLoginBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();

        return new LoginBean.LoginData(req.getParameter("username"), req.getParameter("password"));
    }

    public static EmptyData empty() {
        return new EmptyData();
    }
}
