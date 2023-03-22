package com.innowise.ejbtask.mapper;

import com.innowise.ejbtask.beans.Bean;
import com.innowise.ejbtask.beans.LoginBean;
import com.innowise.ejbtask.beans.RegisterBean;
import com.innowise.ejbtask.beans.RemoveUserBean;
import com.innowise.ejbtask.beans.data.DefaultData;
import com.innowise.ejbtask.beans.data.EmptyData;
import com.innowise.ejbtask.command.RequestAware;
import com.innowise.ejbtask.util.RequestUtil;

public class InputDataMapper {

    public static Bean.InputData toRegisterBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();

        return new RegisterBean.RegisterData(req.getParameter("username"), req.getParameter("password"), "index");
    }


    public static Bean.InputData toLoginBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();

        return new LoginBean.LoginData(req.getParameter("username"), req.getParameter("password"), "home");
    }


    public static Bean.InputData toRemoveUserBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();
        var queryMap = RequestUtil.query(req);

        return new RemoveUserBean.DeleteUserData(Integer.parseInt(queryMap.get("id")));
    }


    public static Bean.InputData toUsersBeanInputData(RequestAware requestAware) {
        var req = requestAware.getRequest();

        return new DefaultData("list", "", "");
    }


    public static EmptyData empty() {
        return new EmptyData();
    }
}
