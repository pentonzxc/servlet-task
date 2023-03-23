package com.innowise.ejbtask.mapper;

import com.innowise.ejbtask.beans.LoginBean;
import com.innowise.ejbtask.beans.RegisterBean;
import com.innowise.ejbtask.beans.RemoveUserBean;
import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.beans.data.DefaultData;
import com.innowise.ejbtask.beans.data.EmptyData;
import com.innowise.ejbtask.wrapper.RequestAware;
import com.innowise.ejbtask.util.RequestUtil;

public class InputDataMapper {

    public static RequestBean.InputData toRegisterBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();

        return new RegisterBean.RegisterData(
                req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("email"),
                req.getParameter("country"),
                "index"
        );
    }


    public static RequestBean.InputData toLoginBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();

        return new LoginBean.LoginData(req.getParameter("username"), req.getParameter("password"), "home");
    }


    public static RequestBean.InputData toRemoveUserBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();
        var queryMap = RequestUtil.query(req);

        return new RemoveUserBean.DeleteUserData(Integer.parseInt(queryMap.get("id")));
    }


    public static RequestBean.InputData toLogoutBeanInputData(RequestAware requestAware) {
        return new DefaultData("index", "", "");
    }


    public static EmptyData empty() {
        return new EmptyData();
    }
}
