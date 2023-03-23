package com.innowise.ejbtask.beans;


import com.innowise.ejbtask.beans.data.DefaultData;
import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.wrapper.RequestAware;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class LogoutBean implements RequestBean {

    @Override
    public OutputData perform(InputData data, RequestAware request) {
        request.getRequest().getSession(false).removeAttribute("role");

        return new DefaultData(data.to(), "", "");
    }
}
